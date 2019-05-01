package com.greenhair.template.domain.match;

import java.util.List;

import com.greenhair.template.domain.team.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchRepository extends JpaRepository<Match, Long> {

	List<Match> findAllByOrderByMatchDateDesc();

	@Query("select m from Match m where m.homeTeam.id = ?1 or m.awayTeam.id = ?1")
	List<Match> findAllByTeamByOrderByMatchDateDesc(long team);

	@Query("select m from Match m where m.league.id = ?1")
	List<Match> findAllByLeagueIdByOrderByMatchDateDesc(long league);

}