package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.MUserinfo;
import com.ragdoll.cloudmeeting.dao.MUserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.dao")
public class MuserInfoServiceImpl implements MuserInfoService{

    @Autowired
    private MUserInfoDao muserInfoDao;


    @Override
    public String isExitUserinfo(String mail,String name){
       try {
           MUserinfo muserInfo = muserInfoDao.findByMusername(name);
           MUserinfo musers = muserInfoDao.findByMemail(mail);
           if(muserInfo == null) {
               if(!(musers==null)){
                   return "邮箱已经存在";
               }
               else {
                   return "邮箱可以用来注册";
               }
           }else{
               return "用户名已经存在";
           }
       }catch (Exception e){
           return "查询失败";
       }
   }

    //添加用户信息
    @Override
    public String addUserInfo(MUserinfo m){
       try {
           MUserinfo muserInfo = muserInfoDao.findByMusername(m.getMname());
           MUserinfo musers = muserInfoDao.findByMemail(m.getMemail());
           if(muserInfo == null) {
               if(!(musers==null)){
                   return "邮箱已被注册";
               }
               else {
                   muserInfoDao.save(m);
                   return "注册成功";
               }
           }else{
               return "该用户名已经存在";
           }
       }catch (Exception e){
            return "系统原因，注册失败" + e;
       }
   }

    //通过用户名查找信息
    @Override
    public MUserinfo findByUsername(String name){
        return muserInfoDao.findByMusername(name);
   }

    //修改用户信息
    @Override
    public String changeUserinfo(String name, Map<String,String> map){
       MUserinfo userinfo = muserInfoDao.findByMusername(name);
       if(userinfo == null){
            return "未查找到用户";
       }
       try{
           userinfo.setCompanyid(map.get("mcompanyid"));
           userinfo.setMposition(map.get("mposition"));
           userinfo.setMaddress(map.get("maddress"));
           userinfo.setMtel(map.get("mtel"));
           muserInfoDao.save(userinfo);
           return "修改成功";
       }catch (Exception e){
           return "修改失败";
       }
   }

    //通过公司ID查找公司所有人员信息
    @Override
    public List<MUserinfo> findUserInfoByCId(String cid){
       List<MUserinfo> users = muserInfoDao.findAllByCompanyid(cid);
       return users;
   }

    //通过邮箱查找人员信息
    @Override
    public MUserinfo findUserInfoByEmail(String mail){
        MUserinfo userinfo = muserInfoDao.findByMemail(mail);
        return userinfo;
    }

}
