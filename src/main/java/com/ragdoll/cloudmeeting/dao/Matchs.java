package com.ragdoll.cloudmeeting.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(MatchKY.class)
public class Matchs {

    @Id
    private Integer conid;
    @Id
    private String musername;
    private String name;
    private String conname;
    private Integer state;
    private Integer constate;

    public Matchs(){

    }

    public Integer getConid() {
        return conid;
    }

    public void setConid(Integer conid) {
        this.conid = conid;
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConname() {
        return conname;
    }

    public void setConname(String conname) {
        this.conname = conname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getConstate() {
        return constate;
    }

    public void setConstate(Integer constate) {
        this.constate = constate;
    }
}
