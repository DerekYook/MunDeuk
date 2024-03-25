
CREATE TABLE IF NOT EXISTS member(
                       member_id bigint primary key NOT NULL auto_increment,
                       nick_name varchar2(10) NOT NULL,
                       email varchar2(50) NOT NULL,
                       password varchar2(20) NOT NULL,
                       acc_time varchar2(10),
                       member_auth varchar2(10) NOT NULL,
                       member_status varchar2(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS board(
                      board_seq bigint primary key NOT NULL auto_increment,
                      title varchar2(50) NOT NULL,
                      content varchar2(200) NOT NULL,
                      board_ctgr varchar2(10) NOT NULL,
                      board_status varchar2(10) NOT NULL,
                      member_id bigint NOT NULL
);