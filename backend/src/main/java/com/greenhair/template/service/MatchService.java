package com.greenhair.template.service;

import org.springframework.stereotype.Service;

@Service
public interface MatchService {

    void loadFromApi(long league_id);
}
