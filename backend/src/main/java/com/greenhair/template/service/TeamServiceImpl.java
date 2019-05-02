package com.greenhair.template.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.greenhair.template.config.ApiConfig;
import com.greenhair.template.domain.league.League;
import com.greenhair.template.domain.league.LeagueRepository;
import com.greenhair.template.domain.team.Team;
import com.greenhair.template.domain.team.TeamRepository;
import com.greenhair.template.util.ConnectionHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

    public static long EPL_LEAGUE_CODE = 2;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ConnectionHelper connectionHelper;

    private final ApiConfig apiConfig;

    @Autowired
	public TeamServiceImpl(ApiConfig apiConfig) {
	    this.apiConfig = apiConfig;
	}
    
    private void saveTeamFromContent(String contentOfTeam) {
        Gson gson = new Gson();
        Type listType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> mapOfResponse = gson.fromJson(contentOfTeam.toString(), listType);
        Map<String, Object> mapOfTeam = (Map<String, Object>)((Map<String, Object>) mapOfResponse.get("api")).get("teams");
        mapOfTeam.entrySet().forEach((item) -> {
            LinkedTreeMap value = (LinkedTreeMap) item.getValue();                
            long id = Long.parseLong(item.getKey());
            Team newTeam = Team.builder()
            .id(id)
            .name(value.get("name").toString())
            .league((League) (leagueRepository.findById(EPL_LEAGUE_CODE).get()))
            .stadium("asdf")
            .build();
            teamRepository.save(newTeam);
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
            HttpURLConnection connectionOfTeam = setConnection(new URL(this.apiConfig.getTeamUrl() + league));
            
            StringBuffer contentOfTeam = connectionHelper.getContentfromConnection(connectionOfTeam);
            
            saveTeamFromContent(contentOfTeam.toString());

            connectionOfTeam.disconnect();

            System.out.println(teamRepository.findAll());
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
	