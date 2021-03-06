package com.greenhair.template.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.PostConstruct;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.greenhair.template.config.ApiConfig;
import com.greenhair.template.domain.league.League;
import com.greenhair.template.domain.league.LeagueRepository;
import com.greenhair.template.domain.match.Match;
import com.greenhair.template.domain.match.MatchRepository;
import com.greenhair.template.domain.team.Team;
import com.greenhair.template.domain.team.TeamRepository;
import com.greenhair.template.util.ConnectionHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service("matchService")
@DependsOn(value = {"apiConfig"})
public class MatchServiceImpl implements MatchService{

    public static long EPL_LEAGUE_CODE = 2;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ConnectionHelper connectionHelper;

    private final ApiConfig apiConfig;

    @Autowired
	public MatchServiceImpl(ApiConfig apiConfig) {
	    this.apiConfig = apiConfig;
	}

    private void saveMatch(String contentOfMatch){
        Gson gson = new Gson();
        Type listType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> mapOfResponse = gson.fromJson(contentOfMatch.toString(), listType);
        Map<String, Object> mapOfMatches = (Map<String, Object>)((Map<String, Object>) mapOfResponse.get("api")).get("fixtures");

        mapOfMatches.entrySet().forEach((item) -> {
            System.out.println(item);
            LinkedTreeMap value = (LinkedTreeMap) item.getValue();
            long leagueId = Long.parseLong(value.get("league_id").toString());
            int goalsHomeTeam = 0;
            int goalsAwayTeam = 0;
            if(value.get("status").toString().equals("Match Finished")) {
                goalsHomeTeam = Integer.parseInt(value.get("goalsHomeTeam").toString());
                goalsAwayTeam = Integer.parseInt(value.get("goalsAwayTeam").toString());
            }
            Match newMatch = Match.builder()
            .matchDate(LocalDateTime.parse((String)(value.get("event_date")),DateTimeFormatter.ISO_OFFSET_DATE_TIME))
            .homeTeam((Team) (teamRepository.findById((Long.parseLong(value.get("homeTeam_id").toString()))).get()))
            .awayTeam((Team) (teamRepository.findById((Long.parseLong(value.get("awayTeam_id").toString()))).get()))
            .goalsHomeTeam(goalsHomeTeam)
            .goalsAwayTeam(goalsAwayTeam)
            .status(value.get("status").toString())
            .stadium(" ")
            .league((League) (leagueRepository.findById(leagueId).get()))
            .season("2018-2019")
            .build();
            matchRepository.save(newMatch);
        });
    }

    private HttpURLConnection setConnection(URL url) throws IOException{
        HttpURLConnection connectionOfTeam = (HttpURLConnection) url.openConnection();
        connectionOfTeam.setRequestProperty("Content-Type", this.apiConfig.getContentsType());
        connectionOfTeam.setRequestProperty("X-RapidAPI-Host", this.apiConfig.getHost());
        connectionOfTeam.setRequestProperty("X-RapidAPI-Key", this.apiConfig.getKey());
        connectionOfTeam.setConnectTimeout(this.apiConfig.getConnectionTime());
        connectionOfTeam.setRequestMethod("GET");
        return connectionOfTeam;
    }

    @Override
    public void loadFromApi(long league) {
        try {
            teamService.loadFromApi(league);
            
            HttpURLConnection connectionOfMatch = setConnection(new URL(this.apiConfig.getMatchUrl() + league));

            StringBuffer contentOfMatch = connectionHelper.getContentfromConnection(connectionOfMatch);
            
            saveMatch(contentOfMatch.toString());

            connectionOfMatch.disconnect();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
}