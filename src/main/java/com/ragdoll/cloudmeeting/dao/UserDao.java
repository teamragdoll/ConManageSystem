//洪涛 2017302580282

package com.ragdoll.cloudmeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<muser,String> {
    muser findByMusername(String username);
}
