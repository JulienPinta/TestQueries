package org.example.testspringdata.data.asso3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ChairRepository
    extends JpaRepository<Chair, Long>, QuerydslPredicateExecutor<Chair>, CustomChairRepository {}
