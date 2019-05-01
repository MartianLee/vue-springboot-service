package com.greenhair.template.domain.match;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.greenhair.template.domain.BaseTimeEntity;
import com.greenhair.template.domain.league.League;
import com.greenhair.template.domain.team.Team;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"home_team_id", "away_team_id"})) 
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper=false)
public class Match extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime matchDate;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "home_team_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team homeTeam;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "away_team_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team awayTeam;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "league_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private League league;

    private String season;
    private String stadium;
    private String status;
    private int goalsHomeTeam;
    private int goalsAwayTeam;

    @Builder
    // public Match(LocalDateTime matchDate, String stadium, int league, String season) {
    public Match(LocalDateTime matchDate, Team homeTeam, Team awayTeam, String status, int goalsHomeTeam, int goalsAwayTeam, String stadium, League league, String season) {
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;
        this.stadium = stadium;
        this.league = league;
        this.season = season;
    }
}