package com.example.rank.rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    RankService rankService;

    @RequestMapping(method = RequestMethod.GET, value = "/count")
    public ResponseEntity getRankCount(){

        return rankService.getRankCount();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getRankForCompanies(){
        return rankService.getRankForCompanies();
    }
}
