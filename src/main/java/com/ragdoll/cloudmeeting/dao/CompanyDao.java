package com.ragdoll.cloudmeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyDao extends JpaRepository<Company,String> {
    Company findByCompanyid(String id);
    List<Company> findAllByCompanyemail(String mail);
}
