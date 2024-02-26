package com.together.MunDeuk.web.Board.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long boardSeq;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(nullable = false)
    private String boardCtgr;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardStatus boardStatus = BoardStatus.Active;

    public enum BoardStatus{
        Active,
        Inactive;
    }
}
