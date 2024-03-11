
CREATE TABLE member(
                       member_id bigint primary key NOT NULL auto_increment,
                       nick_name varchar(10) NOT NULL,
                       email varchar(50) NOT NULL,
                       password varchar(20) NOT NULL,
                       acc_time varchar(10),
                       member_status varchar(10) NOT NULL
);

CREATE TABLE board(
                      board_seq bigint primary key NOT NULL auto_increment,
                      title varchar(50) NOT NULL,
                      content varchar(200) NOT NULL,
                      board_ctgr varchar(10) NOT NULL,
                      board_status varchar(10) NOT NULL,
                      member_id bigint NOT NULL
);