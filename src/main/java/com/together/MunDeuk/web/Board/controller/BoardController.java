package com.together.MunDeuk.web.Board.controller;

import com.together.MunDeuk.web.Board.dto.BoardDto;
import com.together.MunDeuk.web.Board.entity.Board;
import com.together.MunDeuk.web.Board.mapper.BoardMapper;
import com.together.MunDeuk.web.Board.service.BoardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @RequestMapping(value = "/boards")
    public ResponseEntity getAllBoards() {
        List<Board> boardsList = boardService.getBoardLists();

        return new ResponseEntity<>((boardMapper.boardsToBoardResponseDtos(boardsList)), HttpStatus.OK);
    }

    @RequestMapping(value = "/boards/test")
    public String test() {
        return "web/common/main";
    }

    @RequestMapping("/boards/board/{boardSeq}")
    public ResponseEntity getBoard(@PathVariable("boardSeq") @Positive Integer boardSeq) {
        Board board = boardService.getBoard(boardSeq);

        return new ResponseEntity<>((boardMapper.boardToBoardResponseDto(board)), HttpStatus.OK);
    }

    @RequestMapping("/boards/category/{boardCtgr}")
    public ResponseEntity getBoardByCtgr(@PathVariable("boardCtgr") String boardCtgr) {
        List<Board> boardsList = boardService.getBoardListByCtgr(boardCtgr);

        return new ResponseEntity<>((boardMapper.boardsToBoardResponseDtos(boardsList)), HttpStatus.OK);
    }

    @RequestMapping("/boards/user/{memberId}")
    public ResponseEntity getBoardByMember(@PathVariable("memberId") @Positive Integer memberId) {
        List<Board> boardsList = boardService.getBoardListByMemberId(memberId);

        return new ResponseEntity<>((boardMapper.boardsToBoardResponseDtos(boardsList)), HttpStatus.OK);
    }

    @PostMapping("/boards/board")
    public ResponseEntity insertBoard(@Valid @RequestBody BoardDto.Post requestBody){
        Board board = new Board();
        board.setTitle(requestBody.getTitle());
        board.setContent(requestBody.getContent());
        board.setBoardCtgr(requestBody.getBoardCtgr());
        board.setBoardStatus(Board.BoardStatus.Active);
        board.setMemberId(requestBody.getMemberId());

        boardMapper.boardPostDtoToBoard(boardService.setBoard(board));

        return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
    }
}
