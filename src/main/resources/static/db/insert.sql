-- MEMBER TABLE
INSERT INTO member ( member_id, nick_name, email, password, member_auth, member_status, social_type, social_id ) VALUES ( 0, 'JOY', 'test@test.com', 'test1234', 'User', 'Active', null, null );
INSERT INTO member ( member_id, nick_name, email, password, member_auth, member_status, social_type, social_id ) VALUES ( 1, 'LOTTO_PAPA', 'lotto@papa.com', 'test1234', 'Admin', 'Active', null, null );
INSERT INTO member ( member_id, nick_name, email, password, member_auth, member_status, social_type, social_id ) VALUES ( 2, 'backDoor', 'admin@backDoor.com', 'test1234', 'Admin', 'Inactive', null, null );
INSERT INTO member ( member_id, nick_name, email, password, member_auth, member_status, social_type, social_id ) VALUES ( 3, 'backDoor', 'admin@backDoor.com', 'test1234', 'Admin', 'Inactive', null, null );
INSERT INTO member ( member_id, nick_name, email, password, member_auth, member_status, social_type, social_id ) VALUES ( 4, 'backDoor', 'admin@backDoor.com', 'test1234', 'Admin', 'Inactive', null, null );

-- BOARD TABLE
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 0, 'test1', 'test_text1', 'noti', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 1, 'test2', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 2, 'test3', 'test_text1', 'memo', 'Active', 2 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 3, 'test4', 'test_text1', 'memo', 'Inactive', 2 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 4, 'test4', 'test_text1', 'todo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 5, 'test6', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 6, 'test7', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 7, 'test4', 'test_text1', 'noti', 'Active', 2 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 8, 'test9', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 9, 'test10', 'test_text1', 'todo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 10, 'test10', 'test_text1', 'todo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 11, 'test11', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 12, 'test12', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 13, 'test13', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 14, 'test14', 'test_text1', 'map', 'Inactive', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 15, 'test15', 'test_text1', 'map', 'Active', 2 );


--