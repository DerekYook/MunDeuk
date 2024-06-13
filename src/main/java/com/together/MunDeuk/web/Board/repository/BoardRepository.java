package com.together.MunDeuk.web.Board.repository;

import com.together.MunDeuk.web.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value="SELECT * FROM board WHERE BOARD_STATUS = 'Active'", nativeQuery=true)
    List<Board> selectBoards();

    @Query(value="SELECT * FROM board WHERE BOARD_SEQ = :boardSeq", nativeQuery = true)
    Board selectBoard(Integer boardSeq);

    @Query(value="SELECT * FROM board WHERE BOARD_CTGR = :boardCtgr", nativeQuery = true)
    List<Board> selectBoardsByCtgr(String boardCtgr);

    @Query(value="SELECT * FROM board WHERE MEMBER_ID = :memberId", nativeQuery = true)
    List<Board> selectBoardsByMemberId(Integer memberId);

//    @Query(value = "SELECT max(BOARD_SEQ) FROM board", nativeQuery = true)
//    Long selectMaxBoardSeq();
}
