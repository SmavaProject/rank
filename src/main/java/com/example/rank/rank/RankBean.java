package com.example.rank.rank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankBean {
    private String ticker;
    private Integer zacks_rank;
    private String zacks_rank_text;
    private float dividend_yield;
    private float volatility;
    private float pe_ratio;
    private float ask;

    public RankBean(){}

    public RankBean(Rank rank){

    }


}
