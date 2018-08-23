package com.capgemini.domain;

import java.sql.Time;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class EntityListener {
	
	@PostPersist
	public void savePersistTime(AbstractEntity entity) {
        
		entity.setPersistTime(new Time(System.currentTimeMillis()));
		
    }
	
	@PostUpdate
	public void saveUpdateTime(AbstractEntity entity) {
        
		entity.setUpdateTime(new Time(System.currentTimeMillis()));
		
    }

	
}
