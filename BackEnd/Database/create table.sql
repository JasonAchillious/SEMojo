
-- -----------------------------------------------------
-- Table technology
-- -----------------------------------------------------
DROP TABLE IF EXISTS technology ;

CREATE TABLE IF NOT EXISTS technology (
  id INT NOT NULL,
  contributor TEXT NOT NULL,
  outline VARCHAR(45) NOT NULL,
  lang INT NOT NULL,
  difficulty INT NOT NULL,
  score INT NOT NULL,
  sales_volume INT NOT NULL,
  status VARCHAR(45) NOT NULL,
  create_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP NOT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table code_languages
-- -----------------------------------------------------
DROP TABLE IF EXISTS code_languages ;

CREATE TABLE IF NOT EXISTS code_languages (
  id SERIAL,
  technology_id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  file_extends VARCHAR(45) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tech
    FOREIGN KEY (technology_id)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE UNIQUE INDEX code_languages_id_UNIQUE on code_languages(id ASC);
CREATE UNIQUE INDEX name_UNIQUE on code_languages (name ASC);
CREATE INDEX fk_tech_id_lang on code_languages(technology_id ASC);


-- -----------------------------------------------------
-- Table tech_url
-- -----------------------------------------------------
DROP TABLE IF EXISTS tech_url ;

CREATE TABLE IF NOT EXISTS tech_url (
  id INT NOT NULL,
  tech_id INT NOT NULL,
  language INT NOT NULL,
  keyword VARCHAR(45) NOT NULL,
  file_name VARCHAR(45) NOT NULL,
  file_path VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tech
    FOREIGN KEY (tech_id)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE UNIQUE INDEX file_name_UNIQUE on tech_url (file_name ASC);
CREATE UNIQUE INDEX language_UNIQUE on tech_url (language ASC);
CREATE UNIQUE INDEX file_path_UNIQUE on tech_url (file_path ASC);
CREATE UNIQUE INDEX code_UNIQUE on tech_url (tech_id ASC);
CREATE UNIQUE INDEX code_url_id_UNIQUE on tech_url (id ASC);


-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
  user_id SERIAL,
  username VARCHAR(45) NOT NULL,
  account INT NOT NULL,
  password VARCHAR(45) NOT NULL,
  user_type INT NOT NULL,
  balance INT NOT NULL,
  email VARCHAR(45) NULL,
  QQ INT NULL,
  portrait VARCHAR(45) NULL,
  gender INT NULL,
  birth VARCHAR(45) NULL,
  resume VARCHAR(45) NULL,
  department VARCHAR(45) NULL,
  job VARCHAR(45) NULL,
  location VARCHAR(45) NULL,
  credits INT NOT NULL,
  rank INT NOT NULL,
  status INT NULL,
  reg_time TIMESTAMP NOT NULL,
  this_login_time TIMESTAMP NOT NULL,
  this_login_ip VARCHAR(45) NOT NULL,
  last_login_time TIMESTAMP NOT NULL,
  last_login_ip VARCHAR(45) NOT NULL,
  online_status INT NOT NULL,
  PRIMARY KEY (user_id));


-- -----------------------------------------------------
-- Table tech_ratings
-- -----------------------------------------------------
DROP TABLE IF EXISTS tech_ratings ;

CREATE TABLE IF NOT EXISTS tech_ratings (
  id SERIAL,
  tech_id INT NOT NULL,
  user_id INT NOT NULL,
  score INT NOT NULL,
  content TEXT NOT NULL,
  status VARCHAR(45) NOT NULL,
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY (id, user_id, tech_id),

  CONSTRAINT fk_code_id
    FOREIGN KEY (tech_id)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_user
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE UNIQUE INDEX unique_code_user on tech_ratings(tech_id ASC, user_id ASC);
CREATE INDEX fk_user_id_code on tech_ratings(user_id ASC);


-- -----------------------------------------------------
-- Table tech_tag
-- -----------------------------------------------------
DROP TABLE IF EXISTS tech_tag ;

CREATE TABLE IF NOT EXISTS tech_tag (
  id INT NOT NULL,
  tech_id INT NOT NULL,
  tag VARCHAR(45) NOT NULL,
  detail TEXT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tech
    FOREIGN KEY (tech_id)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX fk_tech_id_tag on tech_tag(tech_id ASC);


-- -----------------------------------------------------
-- Table admin
-- -----------------------------------------------------
DROP TABLE IF EXISTS admin ;

CREATE TABLE IF NOT EXISTS admin (
  admin_id INT NOT NULL,
  account INT NOT NULL,
  password INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (admin_id));


-- -----------------------------------------------------
-- Table user_settings
-- -----------------------------------------------------
DROP TABLE IF EXISTS user_settings ;

CREATE TABLE IF NOT EXISTS user_settings (
  user_id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  val VARCHAR(100) NOT NULL,
  PRIMARY KEY (user_id, name),
  CONSTRAINT fk_user
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table favorite
-- -----------------------------------------------------
DROP TABLE IF EXISTS favorite ;

CREATE TABLE IF NOT EXISTS favorite (
  users INT NOT NULL,
  obj_id INT NOT NULL,
  type INT NOT NULL,
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY (users, obj_id, type),
  CONSTRAINT fk_user
    FOREIGN KEY (users)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tech
    FOREIGN KEY (obj_id)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE  INDEX fk_tech_id_favorite on favorite(obj_id ASC);


-- -----------------------------------------------------
-- Table friends
-- -----------------------------------------------------
DROP TABLE IF EXISTS friends ;

CREATE TABLE IF NOT EXISTS friends (
  users INT NOT NULL,
  friend INT NOT NULL,
  tag INT NULL,
  method INT NULL,
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY (users, friend),
  CONSTRAINT fk_user
    FOREIGN KEY (friend)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_users
    FOREIGN KEY (friend)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table forums
-- -----------------------------------------------------
DROP TABLE IF EXISTS forums ;

CREATE TABLE IF NOT EXISTS forums (
  forums_id INT NOT NULL,
  title VARCHAR(45) NOT NULL,
  outline VARCHAR(45) NULL,
  status INT NOT NULL,
  PRIMARY KEY (forums_id));


-- -----------------------------------------------------
-- Table topic
-- -----------------------------------------------------
DROP TABLE IF EXISTS topic ;

CREATE TABLE IF NOT EXISTS topic (
  topic_id INT NOT NULL,
  host INT NOT NULL,
  forum INT NOT NULL,
  technology INT NOT NULL,
  create_time TIMESTAMP NOT NULL,
  title VARCHAR(45) NOT NULL,
  tags VARCHAR(45) NOT NULL,
  view_count INT NOT NULL,
  post_count INT NOT NULL,
  last_post_id INT NULL,
  last_post_user INT NULL,
  last_post_time TIMESTAMP NULL,
  as_top INT NOT NULL,
  as_good INT NOT NULL,
  status INT NOT NULL,
  options INT NULL,
  PRIMARY KEY (topic_id),

  CONSTRAINT fk_host
    FOREIGN KEY (host)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_forum
    FOREIGN KEY (forum)
    REFERENCES forums (forums_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tech
    FOREIGN KEY (technology)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX fk_host_id_topic on topic(host ASC);
CREATE INDEX fk_forum_id_topic on topic(forum ASC);
CREATE INDEX fk_tech_id_topic on topic(technology ASC);


-- -----------------------------------------------------
-- Table posts
-- -----------------------------------------------------
DROP TABLE IF EXISTS posts ;

CREATE TABLE IF NOT EXISTS posts (
  post_id INT NOT NULL,
  users INT NOT NULL,
  username VARCHAR(45) NOT NULL,
  user_email VARCHAR(45) NULL,
  user_url VARCHAR(45) NULL,
  tag VARCHAR(45) NULL,
  tech INT NOT NULL,
  topic INT NOT NULL,
  create_time TIMESTAMP NOT NULL,
  title VARCHAR(45) NOT NULL,
  content TEXT NULL,
  options INT NULL,
  status INT NOT NULL,
  PRIMARY KEY (post_id, users),

  CONSTRAINT fk_user
    FOREIGN KEY (users)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_topic
    FOREIGN KEY (topic)
    REFERENCES topic (topic_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tech
    FOREIGN KEY (tech)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX fk_user_id_post on posts(users ASC);
CREATE INDEX fk_topic_id_post on posts(topic ASC);
CREATE INDEX fk_tech_id_post on posts(tech ASC);

-- -----------------------------------------------------
-- Table msg
-- -----------------------------------------------------
DROP TABLE IF EXISTS msg ;

CREATE TABLE IF NOT EXISTS msg (
  msg_id INT NOT NULL,
  sender INT NOT NULL,
  receiver INT NOT NULL,
  type INT NULL,
  content VARCHAR(1000) NULL,
  send_time TIMESTAMP NOT NULL,
  read_time TIMESTAMP NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (msg_id),

  CONSTRAINT fk_user
    FOREIGN KEY (sender)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_users
    FOREIGN KEY (receiver)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table transaction
-- -----------------------------------------------------
DROP TABLE IF EXISTS transaction ;

CREATE TABLE IF NOT EXISTS transaction (
  users INT NOT NULL,
  obj_id INT NOT NULL,
  type INT NOT NULL,
  create_time TIMESTAMP NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (users, obj_id, type),

  CONSTRAINT fk_user
    FOREIGN KEY (users)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tech
    FOREIGN KEY (obj_id)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX fk_tech_id_tran on transaction(obj_id ASC);

-- -----------------------------------------------------
-- Table own
-- -----------------------------------------------------
DROP TABLE IF EXISTS own ;

CREATE TABLE IF NOT EXISTS own (
  users INT NOT NULL,
  tech INT NOT NULL,
  type INT NOT NULL,
  upload_time TIMESTAMP NOT NULL,
  PRIMARY KEY (users, tech, type),

  CONSTRAINT fk_user
    FOREIGN KEY (users)
    REFERENCES users (user_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_tech
    FOREIGN KEY (tech)
    REFERENCES technology (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
CREATE INDEX fk_tech_id_own on own(tech ASC);


