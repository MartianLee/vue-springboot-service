package com.greenhair.template.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.greenhair.template.config.ApiConfig;
import com.greenhair.template.domain.ResponseVO;
import com.greenhair.template.domain.league.League;
import com.greenhair.template.domain.league.LeagueRepository;
import com.greenhair.template.domain.match.Match;
import com.greenhair.template.domain.match.MatchRepository;
import com.greenhair.template.domain.team.Team;
import com.greenhair.template.domain.team.TeamRepository;
import com.greenhair.template.service.MatchService;
import com.greenhair.template.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private HashMap<String, String> footballApi = new HashMap<String, String>();
    public static long EPL_LEAGUE_CODE = 2;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;

    @GetMapping("/")
    public ResponseVO<?> getMatch(@RequestParam(required = false) Long league, @RequestParam(required = false) Long team) {
        ResponseVO<List<Match>> resp = new ResponseVO<>();
        List<Match> match = null;
        try {
            if(league != null && team == null){
                match = matchRepository.findAllByLeagueIdByOrderByMatchDateDesc(league);
            }else if(league == null && team != null){
                match = matchRepository.findAllByTeamByOrderByMatchDateDesc(team);
            }else {
                match = matchRepository.findAllByOrderByMatchDateDesc();
            }
            resp.setMessage("success");
            resp.setResponse(match);
            return resp;
        } catch (Exception e) {
            resp.setMessage("failed");
            return resp;
        }
    }

    @GetMapping("{id}")
    public ResponseVO<?> getMatch(@PathVariable long id) {
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
            matchService.loadFromApi(EPL_LEAGUE_CODE);
            System.out.println("Match Loaded");
            return "success";
        } catch(Exception e) {
            System.out.println(e);
            return "falied";
        }
    }
    public static void main(String args[]) {
        
        MatchController mc = new MatchController();
        mc.refresh("asdfasdf");
        System.out.println();
    } 
}
