ALTER TABLE hut ADD COLUMN short_name VARCHAR(5) NOT NULL DEFAULT 'T/H';

UPDATE hut SET short_name='HB' WHERE name='Huldrebakken';
UPDATE hut SET short_name='TH' WHERE name='Trollhaugen';
