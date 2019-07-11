package com.ragdoll.cloudmeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MUserInfoDao extends JpaRepository<MUserinfo,String> {
    MUserinfo findByMusername(String musername);
    MUserinfo findByMemail(String memail);
    List<MUserinfo> findAllByCompanyid(String id);
}
