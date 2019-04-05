package com.greenhair.template.domain.matches;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.greenhair.template.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper=false)
public class Match extends BaseTimeEntity {

    private @Id @GeneratedValue Long id;
    private LocalDateTime matchDate;
    private Long homeTeam;
    private Long awayTeam;
    private Long stadium;
    private Long league;
    private String season;

    @Builder
    public Match(LocalDateTime matchDate, Long homeTeam, Long awayTeam, Long stadium, Long league, String season) {
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadium = stadium;
        this.league = league;
        this.season = season;
    }
}