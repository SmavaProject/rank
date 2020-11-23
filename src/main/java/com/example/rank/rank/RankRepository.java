package com.example.rank.rank;

import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;

public interface RankRepository extends CrudRepository<Rank, String> {

    Rank findByDateAndCompanyId(Timestamp date, Long companyId);
}
