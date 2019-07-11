package com.ragdoll.cloudmeeting.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer conid;
    private String conname;
    private String coninfo;
    private String condate;
    private String conaddress;
    private String companyid;


    public Meeting(){
    }

    public String getCompanyid() {
        return companyid;
    }

    public Integer getConid() {
        return conid;
    }

    public void setConid(Integer conid) {
        this.conid = conid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getConname() {
        return conname;
    }

    public String getConinfo() {
        return coninfo;
    }

    public String getCondate() {
        return condate;
    }

    public String getConaddress() {
        return conaddress;
    }

    public void setConname(String conname) {
        this.conname = conname;
    }

    public void setConinfo(String coninfo) {
        this.coninfo = coninfo;
    }

    public void setCondate(String condate) {
        this.condate = condate;
    }

    public void setConaddress(String conaddress) {
        this.conaddress = conaddress;
    }

}
