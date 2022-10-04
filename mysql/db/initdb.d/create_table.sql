CREATE TABLE restaurant (
    rid         VARCHAR(36) NOT NULL,
    name		VARCHAR(30)  NOT NULL,
    location	VARCHAR(30)  NOT NULL,
    star		INT	default 0,
    concept		VARCHAR(10)  NOT NULL,
    PRIMARY KEY (rid),
	INDEX idx_restaurant_name (name)
);

CREATE TABLE menu (
    mid         VARCHAR(36) NOT NULL,
    rid         VARCHAR(36) NOT NULL,
    name		VARCHAR(40)  NOT NULL,
    star		INT	default 0,
    cost		INT	default 0,
    PRIMARY KEY (mid),
	INDEX idx_menu_name (name)
);