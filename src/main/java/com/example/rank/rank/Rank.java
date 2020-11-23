package com.example.rank.rank;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name="rank")
public class Rank implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName = Company.COLUMN_COMPANY_ID,
            insertable = false, updatable = false, nullable = false)
    private Company company;

    @Column(name="company_id", nullable = false)
    private Long companyId;

    @Column(name="date", nullable = false)
    private Timestamp date;

    @Column(name="rank", nullable = true)
    private Integer rank;

    @Column(name="zacks_rank_text", nullable = true)
    private String rankText;

    @Column(name="dividend_yield", nullable = true)
    private float dividendYield;

    @Column(name="volatility", nullable = true)
    private float volatility;

    @Column(name="pe_ratio", nullable = true)
    private float peRatio;

    @Column(name="ask", nullable = true)
    private float ask;

    @Transient
    private String ticker;

    public Rank(){}

    public Rank(LinkedHashMap<String, Object> map){
        String ticker = (String) map.get("ticker");
        this.ticker = ticker;
        Integer zacks_rank = Integer.valueOf((String)map.get("zacks_rank"));
        this.rank = zacks_rank;
        String zacks_rank_text = (String) map.get("zacks_rank_text");
        this.rankText = zacks_rank_text;
        float dividend_yield = Float.parseFloat((String) map.get("dividend_yield"));
        this.dividendYield = dividend_yield;

        Object innerMap = map.get("source");
        if (innerMap != null && innerMap.getClass().equals(LinkedHashMap.class)){
            Object sungardMap = ((LinkedHashMap) innerMap).get("sungard");
            if (sungardMap != null && sungardMap.getClass().equals(LinkedHashMap.class)){
                float volatility = Float.parseFloat((String) ((LinkedHashMap) sungardMap).get("volatility"));
                this.volatility = volatility;
                float pe_ratio = Float.parseFloat((String) ((LinkedHashMap) sungardMap).get("pe_ratio"));
                this.peRatio = pe_ratio;
                float ask = Float.parseFloat((String) ((LinkedHashMap) sungardMap).get("ask"));
                this.ask = ask;
            }
        }

        try {
            String updatedDate = (String) map.get("updated");
            if(updatedDate!= null) {
                Date date = new SimpleDateFormat("MMM dd, yyyy hh:mm a").parse(updatedDate);
                this.date = new Timestamp(date.getTime());
            }

        }catch (Exception e){

        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRankText() {
        return rankText;
    }

    public void setRankText(String rankText) {
        this.rankText = rankText;
    }

    public float getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(float dividendYield) {
        this.dividendYield = dividendYield;
    }

    public float getVolatility() {
        return volatility;
    }

    public void setVolatility(float volatility) {
        this.volatility = volatility;
    }

    public float getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(float peRatio) {
        this.peRatio = peRatio;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
