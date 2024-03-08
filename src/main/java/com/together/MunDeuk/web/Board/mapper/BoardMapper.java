package com.together.MunDeuk.web.Board.mapper;

import com.together.MunDeuk.web.Board.dto.BoardDto;
import com.together.MunDeuk.web.Board.entity.Board;
import com.together.MunDeuk.web.Member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    List<BoardDto.Response> boardsToBoardResponseDtos(List<Board> boards);
    BoardDto.Response boardToBoardResponseDto(Board board);
    BoardDto.Post boardPostDtoToBoard (Board board);
}
