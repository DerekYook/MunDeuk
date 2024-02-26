package com.together.MunDeuk.web.Board.repository;

import com.together.MunDeuk.web.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value="select * from Board where BOARD_STATUS = 'Active'", nativeQuery=true)
    List<Board> selectBoards();

    List<Board> findAll();
}
