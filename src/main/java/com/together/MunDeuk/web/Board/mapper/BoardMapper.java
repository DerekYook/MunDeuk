package com.together.MunDeuk.web.Board.mapper;

import com.together.MunDeuk.web.Board.dto.BoardDto;
import com.together.MunDeuk.web.Board.entity.Board;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    List<BoardDto.Response> boardsToboardResponseDtos(List<Board> boards);
}
