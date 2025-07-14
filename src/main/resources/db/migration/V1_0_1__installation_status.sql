ALTER TABLE installation ADD COLUMN active boolean DEFAULT true;

ALTER TABLE installation_version_info DROP CONSTRAINT fk_installation;
ALTER TABLE installation_metrics DROP CONSTRAINT fk_installation;

ALTER TABLE installation_metrics ADD COLUMN hostname varchar;
UPDATE installation_metrics im SET hostname = i.hostname FROM installation i WHERE im.dv_hub_id = i.dv_hub_id;
ALTER TABLE installation_metrics ALTER COLUMN hostname SET NOT NULL;

ALTER TABLE installation_version_info ADD COLUMN hostname varchar;
UPDATE installation_version_info iv SET hostname = i.hostname FROM installation i WHERE iv.dv_hub_id = i.dv_hub_id;
ALTER TABLE installation_version_info ALTER COLUMN hostname SET NOT NULL;


WITH ranked_installations AS (
    SELECT dv_hub_id,
    ROW_NUMBER() OVER (PARTITION BY hostname ORDER BY dv_hub_id) AS rank
    FROM installation
    WHERE hostname IS NOT NULL
)
DELETE FROM installation
    WHERE dv_hub_id IN (
    SELECT dv_hub_id
    FROM ranked_installations
    WHERE rank > 1
);

ALTER TABLE installation ALTER COLUMN hostname SET NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS installation_hostname ON installation(hostname);

ALTER TABLE installation_metrics ADD CONSTRAINT fk_installation FOREIGN KEY (hostname) REFERENCES installation(hostname);
ALTER TABLE installation_version_info ADD CONSTRAINT fk_installation FOREIGN KEY (hostname) REFERENCES installation(hostname);

ALTER TABLE installation_version_info DROP COLUMN dv_hub_id;
ALTER TABLE installation_metrics DROP COLUMN dv_hub_id;
ALTER TABLE installation DROP COLUMN dv_hub_id;

SELECT hostname, DATE(record_date) AS record_day, COUNT(*) AS count
FROM installation_metrics
GROUP BY hostname, DATE(record_date)
HAVING COUNT(*) > 1
ORDER BY hostname, record_day;