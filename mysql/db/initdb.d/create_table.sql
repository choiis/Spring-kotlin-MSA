CREATE TABLE restaurant (
    rid         SMALLINT NOT NULL AUTO_INCREMENT,
    name		VARCHAR(30)  NOT NULL UNIQUE,
    location	VARCHAR(30)  NOT NULL,
    star		INT	default 0,
    concept		VARCHAR(10)  NOT NULL,
    PRIMARY KEY (rid)
);