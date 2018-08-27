package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.types.CooperationTO;

@Component
public class CooperationMapper {
	
	@PersistenceContext
	EntityManager em;
	
	public CooperationEntity mapOnEntity(CooperationTO mappedFrom){
		CooperationEntity mappedOn = CooperationEntity.newBuilder()
				.withId(mappedFrom.getId())
				.withVersion(mappedFrom.getVersion())
				.withActor(em.getReference(ActorEntity.class, mappedFrom.getActor()))
				.withStudio(em.getReference(StudioEntity.class, mappedFrom.getStudio()))
				.withEffectiveDate(mappedFrom.getEffectiveDate())
				.withExpirationDate(mappedFrom.getExpirationDate())
				.build();
		return mappedOn;
		
	}
	
	public List<Long> mapOnIds(List<CooperationEntity> entities){
		if(entities == null){
			return null;
		}
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
	
	public List<CooperationEntity> mapOnEntities(List<Long> ids){
		if(ids == null){
			return null;
		}
		return ids.stream().map(id -> em.getReference(CooperationEntity.class, id)).collect(Collectors.toList());
	}
	
}
