package com.greenhair.template.domain.team;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.greenhair.template.domain.BaseTimeEntity;
import com.greenhair.template.domain.league.League;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Team extends BaseTimeEntity {

    @Id
    private Long id;
    
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "league_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private League league;
    private String stadium;

    @Builder
    public Team(Long id, String name, League league, String stadium) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.stadium = stadium;
    }
}