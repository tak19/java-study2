CREATE SCHEMA IF NOT EXISTS `ssafysupplement` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

USE `ssafysupplement` ;

create table todo (
    no int auto_increment,
    content varchar(200) not null,
    PRIMARY KEY (no)
);

select * from todo;