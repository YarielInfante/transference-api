CREATE TABLE customer
(
  id        INT         NOT NULL AUTO_INCREMENT,
  name      varchar(50) NOT NULL,
  last_name varchar(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE account
(
  id          INT NOT NULL AUTO_INCREMENT,
  number      VARCHAR(50) NOT NULL UNIQUE,
  customer_id INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE journal
(
  id      INT  NOT NULL AUTO_INCREMENT,
  type_id INT  NOT NULL,
  date    DATE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE transaction_type
(
  id   INT          NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE transaction
(
  id         INT       NOT NULL AUTO_INCREMENT,
  account_id INT       NOT NULL,
  journal_id INT       NOT NULL,
  amount     DECIMAL   NOT NULL,
  date       TIMESTAMP NOT NULL,
  reference  varchar(50),
  PRIMARY KEY (id)
);

ALTER TABLE account
  ADD CONSTRAINT account_fk0 FOREIGN KEY (customer_id) REFERENCES customer (id);

ALTER TABLE journal
  ADD CONSTRAINT journal_fk0 FOREIGN KEY (type_id) REFERENCES transaction_type (id);

ALTER TABLE transaction
  ADD CONSTRAINT transaction_fk0 FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE transaction
  ADD CONSTRAINT transaction_fk1 FOREIGN KEY (journal_id) REFERENCES journal (id);
