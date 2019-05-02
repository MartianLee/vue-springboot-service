package com.greenhair.template.service;

import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    void loadFromApi(long league);
}