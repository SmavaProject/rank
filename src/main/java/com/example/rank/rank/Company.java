package com.example.rank.rank;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class Company implements Serializable {

    public static final String COLUMN_COMPANY_ID= "company_id";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="company_id", nullable = false)
    private Long companyId;

    @Column(name="company_name", nullable = true)
    private String companyName;

    @Column(name="ticker", nullable = false)
    private String ticker;

    @Column(name="market_cap", nullable = true)
    private String marketCap;


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }
}
