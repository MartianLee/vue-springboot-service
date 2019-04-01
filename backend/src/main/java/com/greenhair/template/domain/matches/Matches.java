package com.greenhair.template.domain.matches;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.greenhair.template.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Matches extends BaseTimeEntity {

    private @Id @GeneratedValue Long id;
    private LocalDateTime matchDate;
    private Long homeTeam;
    private Long awayTeam;
    private Long stadium;
    private Long league;
    private String season;

    @Builder
    public Matches(LocalDateTime matchDate, Long homeTeam, Long awayTeam, Long stadium, Long league, String season) {
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadium = stadium;
        this.league = league;
        this.season = season;
    }
}