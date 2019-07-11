package com.ragdoll.cloudmeeting.Test;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class PeopleKY implements Serializable {
    private String name;
    private String id;

    public PeopleKY(){

    }

    public PeopleKY(String id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleKY)) return false;
        PeopleKY peopleKY = (PeopleKY) o;
        return getName().equals(peopleKY.getName()) &&
                getId().equals(peopleKY.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}
