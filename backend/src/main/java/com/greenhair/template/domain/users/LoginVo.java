package com.greenhair.template.domain.users;

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
public class LoginVO extends BaseTimeEntity {
    private String email;
    private String name;
}