package com.tom.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecpCommonResponseHeader {

	private String appid;
    private String txCode;
    private String txserverDateTime;
    private String txserverCode;
    private String code;
    
}
