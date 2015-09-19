INSERT INTO address (address_row, city, zip_code, address_id, )
VALUES ('S:t Eriksg. 56 A', 11234, 'Stockholm', 1);

INSERT INTO bar (address_id, description, name, rating, bar_id)
VALUES (1, 'Nice place', 'Placet', 5, 1);

INSERT INTO beer (description, name, beer_id)
VALUES ('Schysst IPA', 'Biran', 1);

INSERT INTO bar_p_beer (bar_id, beer_id)
VALUES (1, 1);

