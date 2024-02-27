package com.together.MunDeuk.web.Board.controller;

import com.together.MunDeuk.web.Board.entity.Board;
import com.together.MunDeuk.web.Board.mapper.BoardMapper;
import com.together.MunDeuk.web.Board.service.BoardService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
@Validated
@Slf4j
public class BoardController {
    private final static String BOARDS_DEFAULT_URL = "/boards";
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @GetMapping("/")
    public ResponseEntity getAllBoards() {
        List<Board> boardsList = boardService.getBoardLists();

        return new ResponseEntity<>((boardMapper.boardsToBoardResponseDtos(boardsList)), HttpStatus.OK);
    }

    @GetMapping("/board/{boardSeq}")
    public ResponseEntity getBoard(@PathVariable("boardSeq") @Positive Integer boardSeq) {
        Board board = boardService.getBoard(boardSeq);

        return new ResponseEntity<>((boardMapper.boardToBoardResponseDto(board)), HttpStatus.OK);
    }

    @GetMapping("/category/{boardCtgr}")
    public ResponseEntity getBoardByCtgr(@PathVariable("boardCtgr") String boardCtgr) {
        List<Board> boardsList = boardService.getBoardListByCtgr(boardCtgr);

        return new ResponseEntity<>((boardMapper.boardsToBoardResponseDtos(boardsList)), HttpStatus.OK);
    }

    @GetMapping("/user/{memberId}")
    public ResponseEntity getBoardByMember(@PathVariable("memberId") @Positive Integer memberId) {
        List<Board> boardsList = boardService.getBoardListByMemberId(memberId);

        return new ResponseEntity<>((boardMapper.boardsToBoardResponseDtos(boardsList)), HttpStatus.OK);
    }
}
