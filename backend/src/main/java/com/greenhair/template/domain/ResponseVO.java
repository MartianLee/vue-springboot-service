package com.greenhair.template.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ResponseVO<T> {
    
    private @Id @GeneratedValue Long id;

    private String message;
    private boolean check = true;
    private T response;
}