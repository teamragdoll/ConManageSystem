package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.Company;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    Company findUser(String id);
    String isExitEmail(String mail);
    String addCompany(Company company);
    String changeCompanyInfo(Company company);
}
