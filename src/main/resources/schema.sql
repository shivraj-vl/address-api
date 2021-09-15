DROP TABLE IF EXISTS POSTAL_ADDRESS;

CREATE TABLE POSTAL_ADDRESS (
    POST_CODE VARCHAR(128) NOT NULL,
    SUBURB VARCHAR(128) NOT NULL,
    PRIMARY KEY (POST_CODE)
);