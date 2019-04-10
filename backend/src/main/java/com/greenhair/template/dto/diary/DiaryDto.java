package com.greenhair.template.dto.diary;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenhair.template.domain.diaries.Diary;
import com.greenhair.template.domain.users.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {

    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @NotBlank
    private String content;

    private long userId;

    private long matchId;

    // public Diary toEntity() {
    //     return new Diary(title, content, matchId);
    // }
}