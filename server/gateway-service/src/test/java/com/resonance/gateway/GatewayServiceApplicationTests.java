package com.resonance.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GatewayServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void allowsProductionWebCorsPreflight() throws Exception {
		// CN: Web AI 页的流式请求会先走预检，生产域名不能被 gateway 拦成 403。
		// EN: The Web AI streaming request preflights first; the production domain must not be blocked by the gateway as 403.
		// JP: Web AI のストリーミング要求は先にプリフライトされます。本番ドメインを gateway が 403 で拒否してはいけません。
		mockMvc.perform(options("/api/core/ai/chat/stream")
						.header(HttpHeaders.ORIGIN, "https://sunyufei5.art")
						.header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "POST")
						.header(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "content-type,accept"))
				.andExpect(status().isOk())
				.andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "https://sunyufei5.art"));
	}
}
