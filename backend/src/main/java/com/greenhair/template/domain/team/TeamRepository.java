package com.greenhair.template.domain.team;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where t.league.id = ?1")
    List<Team> findAllByLeague(Long league);
}