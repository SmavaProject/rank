package com.example.rank.rank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private final static Logger logger = LoggerFactory.getLogger(Logger.class);
    private final static String zack_base_url = "https://quote-feed.zacks.com/index?t=";

    @Override
    public ResponseEntity getRankCount() {

        Long count = rankRepository.count();
        if (count != null) {
            logger.error("Count " + count);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getRankForCompanies() {

        List<Company> companiesTickers = companyRepository.findAll();

        for (Company c : companiesTickers) {
            try {
                String ticker = c.getTicker();
                logger.info("ticker " + ticker);

                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity = new HttpEntity<String>(headers);

                String rankBeanResponseEntity = restTemplate.exchange(zack_base_url + ticker, HttpMethod.GET, entity, String.class).getBody();

                LinkedHashMap<String, LinkedHashMap<String, Object>> map = mapper.readValue(rankBeanResponseEntity, new TypeReference<LinkedHashMap<String, LinkedHashMap<String, Object>>>() {});

                LinkedHashMap<String, Object> curr = map.get(ticker);
                if (curr != null) {
                    Rank rank = new Rank(curr);

                    Timestamp rankTimeStamp = rank.getDate();
                    Company company = companyRepository.findByTicker(ticker);
                    if (company != null) {
                        Long companyId = company.getCompanyId();
                        if (companyId != null && rankTimeStamp != null) {
                            if (rankRepository.findByDateAndCompanyId(rankTimeStamp, companyId) != null) {
                                logger.error("Ranks is already saved");
                                return null;
                            }
                            rank.setCompanyId(companyId);
                            //rank.setDate(new Timestamp((new Date().getTime())));
                            rankRepository.save(rank);
                        }

                    } else {
                        logger.error("CompanyId not found, ticker: " + ticker);
                    }
                }
            } catch (Exception e) {
                logger.error("Exception while parsing response " + e);
            }

        }







        return null;
    }


}
