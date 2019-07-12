//洪涛 2017302580282

package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.muser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //根据用户名查找用户
    muser findUser(String username);

    //添加用户
    String addUser(muser user);

    //修改用户信息
    String changeInfo(muser user);
}
