package com.greenhair.template.domain.diaries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.greenhair.template.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Diaries extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Diaries(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
