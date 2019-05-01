package com.greenhair.template.service;

import org.springframework.stereotype.Service;

@Service
public interface MatchService {

    boolean loadFromApi(long league_id);
}
