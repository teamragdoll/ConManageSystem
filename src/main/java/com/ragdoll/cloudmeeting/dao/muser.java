//洪涛 2017302580282

package com.ragdoll.cloudmeeting.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class muser {
    @Id
    private String musername;
    private String mpassword;

    public muser(){
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }
}
