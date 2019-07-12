//洪涛 2017302580282

package com.ragdoll.cloudmeeting.dao;

import java.io.Serializable;
import java.util.Objects;

public class MatchKY implements Serializable {

    private Integer conid;
    private String musername;

    public MatchKY(){
    }

    public MatchKY(Integer conid, String username) {
        this.conid = conid;
        this.musername = username;
    }

    public Integer getConid() {
        return conid;
    }

    public void setConid(Integer conid) {
        this.conid = conid;
    }

    public String getUsername() {
        return musername;
    }

    public void setUsername(String username) {
        this.musername = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchKY)) return false;
        MatchKY matchKY = (MatchKY) o;
        return getConid().equals(matchKY.getConid()) &&
                getUsername().equals(matchKY.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConid(), getUsername());
    }
}
