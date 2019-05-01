package com.greenhair.template.domain.diaries;

import com.greenhair.template.domain.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiaryVO extends BaseTimeEntity {
    private String title;
    private String content;
}