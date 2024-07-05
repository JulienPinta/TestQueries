package org.example.testspringdata.data.asso1;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class GroupRepository extends SimpleJpaRepository<Group, Long> {
    public GroupRepository(EntityManager em) {
        super(Group.class, em);
    }
}
