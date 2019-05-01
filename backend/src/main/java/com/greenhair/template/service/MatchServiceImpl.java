package com.greenhair.template.service;

import java.io.BufferedReader;
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

    private final ApiConfig apiConfig;

    @Autowired
	public MatchServiceImpl(ApiConfig apiConfig) {
	    this.apiConfig = apiConfig;
	}

    @Override
    public boolean loadFromApi(long league) {
        if(!teamService.loadFromApi(league)){
            return false;
        }
        try {
            URL urlofMatch = new URL(this.apiConfig.getMatchUrl() + league);
            HttpURLConnection connectionOfMatch = (HttpURLConnection) urlofMatch.openConnection();
            connectionOfMatch.setRequestProperty("Content-Type", this.apiConfig.getContentsType());
            connectionOfMatch.setRequestProperty("X-RapidAPI-Host", this.apiConfig.getHost());
            connectionOfMatch.setRequestProperty("X-RapidAPI-Key", this.apiConfig.getKey());
            connectionOfMatch.setConnectTimeout(10000);
            connectionOfMatch.setRequestMethod("GET");

            BufferedReader bufferMatch = new BufferedReader(
                new InputStreamReader(connectionOfMatch.getInputStream()));
            String inputLineOfMatch;
            StringBuffer contentOfMatch = new StringBuffer();
            while ((inputLineOfMatch = bufferMatch.readLine()) != null) {
                contentOfMatch.append(inputLineOfMatch);
            }
            
            Gson gson = new Gson();
            Type listType = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> mapOfMatches = gson.fromJson(contentOfMatch.toString(), listType);
            Map<String, Object> m = (Map<String, Object>)((Map<String, Object>) mapOfMatches.get("api")).get("fixtures");

            m.entrySet().forEach((item) -> {
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

            bufferMatch.close();
            connectionOfMatch.disconnect();
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
}