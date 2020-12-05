CREATE TABLE IF NOT EXISTS Users (
   id          SERIAL          PRIMARY KEY,
   version     INTEGER         NOT NULL,
   login       VARCHAR(16)     NOT NULL,
   password    VARCHAR(16)     NOT NULL,
   registered  DATE            NOT NULL
);


CREATE TABLE IF NOT EXISTS Topics (
   id          SERIAL          PRIMARY KEY,
   version     INTEGER         NOT NULL,
   title       VARCHAR(64)     NOT NULL,        
   created     DATE            NOT NULL,
   user_id     BIGINT          NOT NULL   REFERENCES Users(id)   
);


CREATE TABLE IF NOT EXISTS Posts (
   id          SERIAL          PRIMARY KEY,
   version     INTEGER         NOT NULL,    
   text        VARCHAR(1024)   NOT NULL,
   created     DATE            NOT NULL,
   user_id     BIGINT          NOT NULL   REFERENCES Users(id),
   topic_id    BIGINT          NOT NULL   REFERENCES Topics(id)   
);

