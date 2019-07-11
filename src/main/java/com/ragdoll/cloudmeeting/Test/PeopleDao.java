package com.ragdoll.cloudmeeting.Test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleDao extends JpaRepository<People,PeopleKY> {


}
