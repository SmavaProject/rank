package com.example.rank.rank;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, String> {

   // @Query(value = "SELECT * from Company c", nativeQuery = true)


    List<Company> findAll();

    Company findByTicker(String ticker);


}
