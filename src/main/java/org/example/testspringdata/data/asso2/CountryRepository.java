package org.example.testspringdata.data.asso2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository
    extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {}
