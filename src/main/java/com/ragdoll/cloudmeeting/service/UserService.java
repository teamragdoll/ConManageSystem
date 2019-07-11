package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.muser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    muser findUser(String username);
    String addUser(muser user);
    String changeInfo(muser user);
}
