package com.greenhair.template.domain.users;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.greenhair.template.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Users extends BaseTimeEntity {

    private @Id @GeneratedValue Long id;
    private String name;
    private String phone;

    @Builder
    public Users(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}