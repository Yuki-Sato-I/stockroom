CREATE TABLE IF NOT EXISTS public.users (
  id UUID NOT NULL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  token TEXT NOT NULL,
  password_digest TEXT NOT NULL,
  activated_at TIMESTAMP,
  activated BOOLEAN NOT NULL,
  status SMALLINT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE ( email, status )
)