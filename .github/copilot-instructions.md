# 20,000 Days Resonance Copilot Instructions

This repository keeps its tool-agnostic project rules in `AGENTS.md` and the files under `instructions/`.

When working in this repository:

- Treat `AGENTS.md` as the primary high-level source of truth for product direction, engineering rules, and repository-wide constraints.
- Treat `instructions/` as the place for stable, default rules that must be followed across tasks.
- Treat `skills/` as repeatable operational workflows, not as policy documents.
- After completing a meaningful stage of work, proactively check whether `skills/`, `instructions/`, and `MEMORY.md` need to be updated.
- Keep frontend production UI text in i18n resources instead of hard-coding Chinese, English, or Japanese inside business components.
- Keep BSSID handling privacy-safe: hash BSSID with SHA-256 before it reaches the backend, and prefer a reusable Hook or equivalent frontend capability module for Wi-Fi-related logic.
- Real business logic in controllers, services, entities, core pages, key state transitions, important algorithm blocks, and critical SQL should carry aligned CN/EN/JP comments or descriptions.

If an editor-specific rule file also exists, treat it as an adapter only. Do not let editor-specific files override the repository rules above.