package com.resonance.core.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resonance.core.ai.AiProperties;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
/**
 * CN: DeepSeek 客户端只做传输和响应解析，业务侧负责拼 prompt、记历史和做并发限制。
 * EN: The DeepSeek client only handles transport and response parsing. Business code owns prompt assembly, history storage, and concurrency limits.
 * JP: DeepSeek クライアントは通信と応答解析だけを担います。prompt 組み立て、履歴保存、並行制限は業務側が担当します。
 */
public class DeepseekClient {

    private final AiProperties properties;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public DeepseekClient(AiProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder()
                .baseUrl(properties.getDeepseek().getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String modelName() {
        return properties.getDeepseek().getModel();
    }

    public Flux<String> streamChat(List<DeepseekMessage> messages) {
        requireApiKey();
        Map<String, Object> body = Map.of(
                "model", properties.getDeepseek().getModel(),
                "stream", true,
                "messages", messages.stream()
                        .map(message -> Map.of("role", message.role(), "content", message.content()))
                        .toList());

        StreamDecoder decoder = new StreamDecoder(objectMapper);
        return webClient.post()
                .uri("/chat/completions")
                .headers(headers -> headers.setBearerAuth(properties.getDeepseek().getApiKey()))
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(body)
                .retrieve()
                .bodyToFlux(String.class)
                .timeout(Duration.ofSeconds(90))
                .concatMap(decoder::decode);
    }

    public Mono<String> complete(List<DeepseekMessage> messages, int maxTokens) {
        requireApiKey();
        Map<String, Object> body = Map.of(
                "model", properties.getDeepseek().getModel(),
                "stream", false,
                "max_tokens", maxTokens,
                "messages", messages.stream()
                        .map(message -> Map.of("role", message.role(), "content", message.content()))
                        .toList());

        return webClient.post()
                .uri("/chat/completions")
                .headers(headers -> headers.setBearerAuth(properties.getDeepseek().getApiKey()))
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(45))
                .map(this::extractFullContent);
    }

    private void requireApiKey() {
        if (properties.getDeepseek().getApiKey() == null || properties.getDeepseek().getApiKey().isBlank()) {
            throw new IllegalStateException("DEEPSEEK_API_KEY is not configured");
        }
    }

    private String extractFullContent(JsonNode root) {
        JsonNode content = root.path("choices").path(0).path("message").path("content");
        return content.isTextual() ? content.asText() : "";
    }

    private static class StreamDecoder {

        private final ObjectMapper objectMapper;
        private final StringBuilder buffer = new StringBuilder();

        StreamDecoder(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        Flux<String> decode(String chunk) {
            if (chunk == null || chunk.isEmpty()) {
                return Flux.empty();
            }
            buffer.append(chunk);
            List<String> deltas = new ArrayList<>();
            int newline;
            while ((newline = buffer.indexOf("\n")) >= 0) {
                String line = buffer.substring(0, newline).trim();
                buffer.delete(0, newline + 1);
                decodeLine(line, deltas);
            }
            if (buffer.length() > 0 && buffer.charAt(0) == '{') {
                decodeLine(buffer.toString().trim(), deltas);
                buffer.setLength(0);
            }
            return Flux.fromIterable(deltas);
        }

        private void decodeLine(String line, List<String> deltas) {
            if (line.isBlank()) {
                return;
            }
            String payload = line.startsWith("data:") ? line.substring(5).trim() : line;
            if ("[DONE]".equals(payload)) {
                return;
            }
            try {
                JsonNode content = objectMapper.readTree(payload).path("choices").path(0).path("delta").path("content");
                if (content.isTextual() && !content.asText().isEmpty()) {
                    deltas.add(content.asText());
                }
            } catch (Exception ignored) {
                // CN: 流式分片可能被网络切开，下一段到达后再解析。
                // EN: Stream chunks can be split by the network; the next chunk may complete the payload.
                // JP: ストリーム断片はネットワークで分割されることがあり、次の断片で payload が完成する場合があります。
            }
        }
    }
}
