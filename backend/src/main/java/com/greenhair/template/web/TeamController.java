package com.greenhair.template.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.league.League;
import com.greenhair.template.domain.league.LeagueRepository;
import com.greenhair.template.domain.match.Match;
import com.greenhair.template.domain.match.MatchRepository;
import com.greenhair.template.domain.team.Team;
import com.greenhair.template.domain.team.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    
    @Autowired
    private LeagueRepository leagueRepository;
    
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/")
    public ResponseVO<?> getTeam(@RequestParam(required = false) Long league) {
        ResponseVO<List<Team>> resp = new ResponseVO<>();
        try {
            List<Team> team;
            if(league != null){
                team = teamRepository.findAllByLeague(league);
            }else {
                team = teamRepository.findAll();
            }
            resp.setMessage("success");
            resp.setResponse(team);
            return resp;
        } catch (Exception e) {
            resp.setMessage("failed");
            return resp;
        }
    }

    @GetMapping("{id}")
    public ResponseVO<?> getTeamById(@PathVariable long id) {
        ResponseVO<Match> resp = new ResponseVO<>();
        try {
            Optional<Match> match = matchRepository.findById(id);
            resp.setMessage("success");
            resp.setResponse(match.get());
            return resp;
        } catch(Exception e){
            resp.setMessage("failed");
            return resp;
        }
    }

    @GetMapping("/refresh")
    public String refresh(String season) {
        try {
            return "success";
        } catch(Exception e) {
            System.out.println(e);
            return "falied";
        }
    } 
}
