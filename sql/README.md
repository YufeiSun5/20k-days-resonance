# SQL Directory Convention

This directory stores PostgreSQL scripts grouped by database ownership.

## Structure

- `profile/`: scripts for the `resonance_profile` database
- `core/`: scripts for the `resonance_core` database

Each database folder contains:

- `create/`: initial creation scripts
- `modify/`: incremental schema change scripts
- `test/`: verification queries

## Naming

- Table scripts: `table_name_v1.sql`, `table_name_v2.sql`
- Shared scripts: `000_database_v1.sql`, `001_extensions_v1.sql`

## Database Split

- `resonance_profile`: account and identity data
- `resonance_core`: journals, spaces, events, recommendations, embeddings

## AI Analysis Placement

AI analysis metadata stays in `resonance_core`.

Reason:

- The current AI capability is an external API call, not an independent database-owning service.
- `emotion_score`, `insight_keywords`, and `journal_embedding` belong to journal lifecycle data.
- Keeping them in `resonance_core` avoids cross-database writes and reduces distributed consistency problems.

## Cross-Database Rule

Do not create database-level foreign keys across `resonance_profile` and `resonance_core`.

- `profile` owns `users` and `auth_identity`
- `core` stores `user_id` as a plain UUID reference without `REFERENCES users(id)`

## General Rules

- Version numbers are table-scoped, not repository-scoped.
- A new change to the same table should create a new versioned file in the matching `modify/` directory.
- Do not overwrite older versions after they are executed in an environment.
