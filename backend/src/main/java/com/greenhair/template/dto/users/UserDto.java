package com.greenhair.template.dto.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenhair.template.domain.users.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @Pattern(regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$")
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(min = 4, max = 15)
    private String password;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    public Users toEntity() {
        return new Users(email, password, name);
    }

    // public Users toEntityWithPasswordEncode(PasswordEncoder bCryptPasswordEncoder) {
    //     return new Users(email, bCryptPasswordEncoder.encode(password), name);
    // }
}