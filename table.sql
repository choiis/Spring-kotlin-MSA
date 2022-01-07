CREATE KEYSPACE keys

         WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor': 1};

CREATE table keys.sm01(
appkey varchar,
userid varchar,
usertype int,
PRIMARY KEY (appkey)
);