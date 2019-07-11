package com.ragdoll.cloudmeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MeetingDao extends JpaRepository<Meeting,String>{

    List<Meeting> findAllByConname(String conname);
    List<Meeting> findAllByCompanyid(String id);
    Meeting findByConid(Integer id);
    @Transactional
    void deleteByConid(Integer id);
}
