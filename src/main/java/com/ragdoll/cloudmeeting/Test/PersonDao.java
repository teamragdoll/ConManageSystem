package com.ragdoll.cloudmeeting.Test;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface PersonDao extends JpaRepository<Person,Integer> {

    @Transactional
    void deleteById(Integer id);
}
