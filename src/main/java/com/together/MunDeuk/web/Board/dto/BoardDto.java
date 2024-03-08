package com.together.MunDeuk.web.Board.dto;

import com.together.MunDeuk.web.Board.entity.Board.BoardStatus;
import lombok.Getter;
import lombok.Setter;

public class BoardDto {
    @Getter
    @Setter
    public static class Post{
        private String title;
        private String content;
        private String boardCtgr;
        private BoardStatus boardStatus;
        private Long memberId;

        public Post(String title, String content, String boardCtgr, BoardStatus boardStatus, Long memberId) {
            this.title = title;
            this.content = content;
            this.boardCtgr = boardCtgr;
            this.boardStatus = boardStatus;
            this.memberId = memberId;
        }
    }

    @Getter
    public static class Response{
        private long boardSeq;
        private String title;
        private String content;
        private String boardCtgr;
        private BoardStatus boardStatus;

        public Response(long boardSeq, String title, String content, String boardCtgr, BoardStatus boardStatus){
            this.boardSeq = boardSeq;
            this.title = title;
            this.content = content;
            this.boardCtgr = boardCtgr;
            this.boardStatus = boardStatus;
        }
    }

}
