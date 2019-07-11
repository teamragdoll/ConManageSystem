package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.muser;
import com.ragdoll.cloudmeeting.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.dao")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public muser findUser(String username) {
        muser user = userDao.findByMusername(username);
        return user;
    }

    @Override
    public String addUser(muser user){
        try{
            muser u = findUser(user.getMusername());
            if(u != null){
                return "已存在";
            }else{
                userDao.save(user);
                return "添加成功";
            }

        }catch (Exception e){
            return "添加失败";
        }
    }

    @Override
    public String changeInfo(muser user){
        userDao.save(user);
        return "修改成功";
    }
}


