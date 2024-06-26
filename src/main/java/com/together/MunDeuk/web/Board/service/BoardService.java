package com.together.MunDeuk.web.Board.service;

import com.together.MunDeuk.web.Board.entity.Board;
import com.together.MunDeuk.web.Board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoardLists(){
        return boardRepository.selectBoards();
    }

    public Board getBoard(Integer boardSeq){
        return boardRepository.selectBoard(boardSeq);
    }

    public List<Board> getBoardListByCtgr(String boardCtgr){
        return boardRepository.selectBoardsByCtgr(boardCtgr);
    }

    public List<Board> getBoardListByMemberId(Integer memberId){
        return boardRepository.selectBoardsByMemberId(memberId);
    }

    public Board setBoard(Board board){
        return boardRepository.save(board);
    }

}
