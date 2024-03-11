-- member TABLE
INSERT INTO member ( nick_name, email, password, member_status ) VALUES ( 'JOY', 'test@test.com', 'test1234', 'Active' );
INSERT INTO member ( nick_name, email, password, member_status ) VALUES ( 'LOTTO_PAPA', 'lotto@papa.com', 'test1234', 'Active' );
INSERT INTO member ( nick_name, email, password, member_status ) VALUES ( 'backDoor', 'admin@backDoor.com', 'test1234', 'Inactive' );


-- board TABLE
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES( 'test1', 'test_text1', 'noti', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES( 'test2', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test3', 'test_text1', 'memo', 'Active', 2 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test4', 'test_text1', 'memo', 'Inactive', 2 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test4', 'test_text1', 'todo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test6', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test7', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test4', 'test_text1', 'noti', 'Active', 2 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test9', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test10', 'test_text1', 'todo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test11', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test12', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test13', 'test_text1', 'memo', 'Active', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test14', 'test_text1', 'map', 'Inactive', 1 );
INSERT INTO board ( title, content, board_ctgr, board_status, member_id ) VALUES ( 'test15', 'test_text1', 'map', 'Active', 2 );

--