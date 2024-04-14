CREATE SCHEMA `watch` ;

CREATE TABLE IF NOT EXISTS `ARTICLES`(
    `id`          INTEGER PRIMARY KEY,
    `title`       VARCHAR(100) NOT NULL,
    `author`      VARCHAR(100) NOT NULL
    );
INSERT INTO `watch`.`articles`
(`id`,
 `title`,
 `author`)
VALUES
    (1,
     "title",
     "author");
