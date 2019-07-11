package com.ragdoll.cloudmeeting.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MUserinfo {

    @Id
    private String musername;
    private String mname;
    private String mtel;
    private String maddress;
    private String companyid;
    private String mposition;
    private String memail;

    public MUserinfo(){
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMtel() {
        return mtel;
    }

    public void setMtel(String mtel) {
        this.mtel = mtel;
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getMposition() {
        return mposition;
    }

    public void setMposition(String mposition) {
        this.mposition = mposition;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }
}
