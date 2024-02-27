-- BOARD TABLE
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES( 1, 'test1', 'test_text1', 'noti', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES( 2, 'test2', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 3, 'test3', 'test_text1', 'memo', 'Active', 2 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 4, 'test4', 'test_text1', 'memo', 'Inactive', 2 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 5, 'test4', 'test_text1', 'todo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 6, 'test6', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 7, 'test7', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 8, 'test4', 'test_text1', 'noti', 'Active', 2 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 9, 'test9', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 10, 'test10', 'test_text1', 'todo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 11, 'test11', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 12, 'test12', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 13, 'test13', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 14, 'test14', 'test_text1', 'map', 'Inactive', 1 );
INSERT INTO board ( board_seq, title, content, board_ctgr, board_status, member_id ) VALUES ( 15, 'test15', 'test_text1', 'map', 'Active', 2 );

-- MEMBER TABLE
INSERT INTO member ( member_id, nick_name, email, password, member_status ) VALUES ( 1, 'JOY', 'test@test.com', 'test1234', 'Active' );
INSERT INTO member ( member_id, nick_name, email, password, member_status ) VALUES ( 2, 'LOTTO_PAPA', 'lotto@papa.com', 'test1234', 'Active' );
INSERT INTO member ( member_id, nick_name, email, password, member_status ) VALUES ( 3, 'backDoor', 'admin@backDoor.com', 'test1234', 'Inactive' );

--