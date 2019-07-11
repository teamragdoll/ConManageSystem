package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.Company;
import com.ragdoll.cloudmeeting.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.dao")
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Override
    public Company findUser(String id){
        Company company = companyDao.findByCompanyid(id);
        return company;
    }

    @Override
    public String isExitEmail(String mail){
        List<Company> companies = companyDao.findAllByCompanyemail(mail);
        if(companies.size() == 0){
            return "邮箱未被注册";
        }else{
            return "邮箱已被注册";
        }
    }

    @Override
    public String addCompany(Company company){
        try {
            companyDao.save(company);
            return "保存成功";
        }catch (Exception e){
            return "保存失败";
        }
    }

    @Override
    public String changeCompanyInfo(Company c){
        Company company = companyDao.findByCompanyid(c.getCompanyid());
        if(company == null){
            return "查询失败";
        }else{
            companyDao.save(c);
            return "修改成功";
        }
    }
}


