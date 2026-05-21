package com.resonance.core.ai;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai")
/**
 * CN: AI 配置集中放在这里，避免 DeepSeek key、模型名和 skill 路径散落在业务代码里。
 * EN: AI configuration is centralized here so the DeepSeek key, model name, and skill path do not leak across business code.
 * JP: AI 設定はここへ集約し、DeepSeek key、モデル名、skill パスが業務コードへ散らばらないようにします。
 */
public class AiProperties {

    private String skillsRoot = "../../data/skills";
    private int maxHistoryMessages = 20;
    private int maxConcurrentPerUser = 2;
    private Agent agent = new Agent();
    private Deepseek deepseek = new Deepseek();

    public String getSkillsRoot() {
        return skillsRoot;
    }

    public void setSkillsRoot(String skillsRoot) {
        this.skillsRoot = skillsRoot;
    }

    public int getMaxHistoryMessages() {
        return maxHistoryMessages;
    }

    public void setMaxHistoryMessages(int maxHistoryMessages) {
        this.maxHistoryMessages = maxHistoryMessages;
    }

    public int getMaxConcurrentPerUser() {
        return maxConcurrentPerUser;
    }

    public void setMaxConcurrentPerUser(int maxConcurrentPerUser) {
        this.maxConcurrentPerUser = maxConcurrentPerUser;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Deepseek getDeepseek() {
        return deepseek;
    }

    public void setDeepseek(Deepseek deepseek) {
        this.deepseek = deepseek;
    }

    public static class Agent {
        private int maxRounds = 3;
        private int maxRequestsPerRound = 4;
        private int noNewContextLimit = 2;
        private int contextCharBudget = 90000;

        public int getMaxRounds() {
            return maxRounds;
        }

        public void setMaxRounds(int maxRounds) {
            this.maxRounds = maxRounds;
        }

        public int getMaxRequestsPerRound() {
            return maxRequestsPerRound;
        }

        public void setMaxRequestsPerRound(int maxRequestsPerRound) {
            this.maxRequestsPerRound = maxRequestsPerRound;
        }

        public int getNoNewContextLimit() {
            return noNewContextLimit;
        }

        public void setNoNewContextLimit(int noNewContextLimit) {
            this.noNewContextLimit = noNewContextLimit;
        }

        public int getContextCharBudget() {
            return contextCharBudget;
        }

        public void setContextCharBudget(int contextCharBudget) {
            this.contextCharBudget = contextCharBudget;
        }
    }

    public static class Deepseek {
        private String apiKey = "";
        private String baseUrl = "https://api.deepseek.com";
        private String model = "deepseek-v4-flash";

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }
}
