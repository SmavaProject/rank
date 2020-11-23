package com.example.rank.rank;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface RankService {

    public ResponseEntity getRankCount();
    public ResponseEntity getRankForCompanies();
}
