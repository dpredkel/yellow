CREATE SEQUENCE users_seq;
CREATE TABLE users
(
  id        BIGINT       NOT NULL DEFAULT nextval('users_seq') PRIMARY KEY,
  uuid      VARCHAR(100) NOT NULL UNIQUE,
  username  VARCHAR(100) NOT NULL UNIQUE,
  password  VARCHAR(150) NOT NULL,
  authority VARCHAR(50)  NOT NULL,
  active    BOOLEAN      NOT NULL
);

----------------------------------------------------------------------------------------

CREATE SEQUENCE runnings_seq;
CREATE TABLE runnings
(
  id       BIGINT       NOT NULL DEFAULT nextval('runnings_seq') PRIMARY KEY,
  uuid     VARCHAR(100) NOT NULL UNIQUE,
  distance INTEGER      NOT NULL,
  duration INTEGER      NOT NULL,
  date     DATE         NOT NULL,
  user_id  BIGINT       NOT NULL,
  CONSTRAINT fk_runnings_users FOREIGN KEY (user_id) REFERENCES users (id)
);

