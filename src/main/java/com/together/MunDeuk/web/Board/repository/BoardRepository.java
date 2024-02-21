package com.together.MunDeuk.web.Board.repository;

import com.together.MunDeuk.web.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
