package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.MatchDao;
import com.ragdoll.cloudmeeting.dao.Matchs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.dao")
public class MatchServiceImpl implements MatchsService {

    @Autowired
    private MatchDao matchDao;

    @Override
    public String saveMatch(Matchs m){
        try {
            matchDao.save(m);
            return "保存成功";
        }catch (Exception e){
            System.out.println(e);
            return "保存失败";
        }
    }

//    @Override
//    public String changeMatchsConstateByConid(Integer state,Integer conid){
//        try {
//            matchDao.update(state, conid);
//            return "修改成功";
//        }catch(Exception e){
//            return "修改失败";
//        }
//    }

    @Override
    public String changeMatchCstateByConidAndName(Integer state,Integer cid,String musername){
        try {
            Matchs matchs = matchDao.findByConidAndMusername(cid, musername);
            matchs.setConstate(state);
            matchDao.save(matchs);
            return "修改成功";
        }catch (Exception e) {
            return "修改失败";
        }
    }

//    @Override
//    public String changeMatchStateByConidAndName(Integer state,Integer conid,String musername){
//        try {
//            Matchs matchs = matchDao.findByConidAndMusername(conid, musername);
//            matchs.setState(state);
//            matchDao.save(matchs);
//            return "修改成功";
//        }catch (Exception e) {
//            return "修改失败";
//        }
//    }

    @Override
    public List<Matchs> findMatchsByMusername(String name){
        return matchDao.findAllByMusername(name);
    }

    @Override
    public Matchs findMatchByConidAndMusername(Integer id,String username){
        return matchDao.findByConidAndMusername(id,username);
    }

    @Override
    public String deleteMatch(Integer id,String username){
        try{
            matchDao.deleteByConidAndMusername(id,username);
            System.out.println("删除成功");
            return "删除成功";
        }catch(Exception e){
            System.out.println(e);
            return "删除失败";
        }
    }

    @Override
    public String getState(Integer conid,String username){
        Matchs matchs = matchDao.findByConidAndMusername(conid, username);
        if(matchs == null){
            return "未报名";
        }else if(matchs.getState() == 1){
            return "邀请未报名";
        }else{
            return "已报名";
        }
    }

    @Override
    public List<Matchs> findByConidAndState(Integer conid,Integer state){
        return matchDao.findAllByConidAndState(conid,state);
    }
}
