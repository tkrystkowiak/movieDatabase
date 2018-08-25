package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.domain.CooperationEntity;

public class CooperationMapper {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Long> mapOnIds(List<CooperationEntity> entities){
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
	
	public List<CooperationEntity> mapOnEntities(List<Long> ids){
		return ids.stream().map(id -> em.getReference(CooperationEntity.class, id)).collect(Collectors.toList());
	}
	
}
