//洪涛 2017302580282

package com.ragdoll.cloudmeeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface MatchDao extends JpaRepository<Matchs,MatchKY> {

    Matchs findByConidAndMusername(Integer id,String username);
    List<Matchs> findAllByMusername(String name);
    List<Matchs> findAllByConidAndState(Integer conid,Integer state);

    @Modifying
    @Transactional
    @Query("update Matchs m set m.constate = :s where m.conid = :id")
    void update(@Param("s") Integer s,@Param("id") Integer id);

    @Transactional
    void deleteByConidAndMusername(Integer id,String name);

    @Transactional
    void deleteByConidAndState(Integer conid,Integer state);

}
