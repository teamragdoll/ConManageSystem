//洪涛 2017302580282

package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.Company;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    //根据公司id查找公司信息
    Company findUser(String id);

    //判断数据库是否已存在邮箱
    String isExitEmail(String mail);

    //添加公司信息
    String addCompany(Company company);

    //修改公司信息
    String changeCompanyInfo(Company company);
}
