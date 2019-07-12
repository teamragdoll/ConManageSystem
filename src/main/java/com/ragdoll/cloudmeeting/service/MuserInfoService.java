//洪涛 2017302580282

package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.MUserinfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MuserInfoService {
    //检查用户是否存在
    String isExitUserinfo(String mail,String name);

    //添加新的用户信息
    String addUserInfo(MUserinfo muserInfo);

    //修改用户信息
    String changeUserinfo(String name, Map<String,String> map);

    //通过公司ID查找公司所有员工的信息
    List<MUserinfo> findUserInfoByCId(String cid);

    //通过邮箱查找用户信息
    MUserinfo findUserInfoByEmail(String mail);

    //通过username查找用户信息
    MUserinfo findByUsername(String name);

}
