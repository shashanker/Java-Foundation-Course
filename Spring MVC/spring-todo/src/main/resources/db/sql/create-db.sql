CREATE TABLE IF NOT EXISTS users (
  id bigint auto_increment NOT NULL,
  username VARCHAR(250) unique NOT NULL,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(10) NULL,
  isUserActive BOOLEAN
);

CREATE TABLE IF NOT EXISTS todos (
  todoId bigint auto_increment NOT NULL,
  userId bigint NOT NULL,
  month varchar(20) NOT NULL,
  day varchar(20) NOT NULL,
  year varchar(20) NOT NULL,
  title VARCHAR(250) NOT NULL,
  description VARCHAR(2000) NOT NULL,
  priority VARCHAR(10) NOT NULL
);
