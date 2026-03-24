CREATE DATABASE resonance_profile;
CREATE DATABASE resonance_core;

\connect resonance_profile;
CREATE EXTENSION IF NOT EXISTS pgcrypto;

\connect resonance_core;
CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE EXTENSION IF NOT EXISTS vector;