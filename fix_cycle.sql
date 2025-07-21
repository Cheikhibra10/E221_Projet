-- 1. Remplace toutes les valeurs NULL par 0
UPDATE cycle SET taux_horaire = 0 WHERE taux_horaire IS NULL;

-- 2. Vérifie s'il reste encore des NULL
DO $$
DECLARE
    null_count INT;
BEGIN
    SELECT COUNT(*) INTO null_count FROM cycle WHERE taux_horaire IS NULL;
    IF null_count > 0 THEN
        RAISE NOTICE 'ATTENTION: Il reste % lignes NULL dans taux_horaire', null_count;
    ELSE
        -- 3. Applique DEFAULT et NOT NULL uniquement si pas de NULL
        EXECUTE 'ALTER TABLE cycle ALTER COLUMN taux_horaire SET DEFAULT 0';
        EXECUTE 'ALTER TABLE cycle ALTER COLUMN taux_horaire SET NOT NULL';
        RAISE NOTICE 'La colonne taux_horaire a été mise à jour avec DEFAULT 0 et NOT NULL';
    END IF;
END
$$;
