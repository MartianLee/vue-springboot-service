package com.greenhair.template.domain.league;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.greenhair.template.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper=false)
public class League extends BaseTimeEntity {

    @Id
    private Long id;
    
    private String name;

    @Builder
    public League(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}