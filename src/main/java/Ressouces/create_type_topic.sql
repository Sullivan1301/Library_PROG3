DO $$ 
BEGIN
    CREATE TYPE Topic AS ENUM ('COMEDY', 'ROMANCE', 'OTHER');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;