package com.capgemini.mappers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.domain.StudioEntity;
import com.capgemini.types.StudioTO;

@Component
public class StudioMapper {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CooperationMapper cooperationMapper;
	
	@Autowired
	MovieMapper movieMapper;
	
	public StudioTO mapOnTO(StudioEntity mappedFrom){
		if(mappedFrom == null){
			return null;
		}
		StudioTO mappedOn = StudioTO.newBuilder()
				.withId(mappedFrom.getId())
				.withVersion(mappedFrom.getVersion())
				.withCountry(mappedFrom.getCountry())
				.withName(mappedFrom.getName())
				.withCooperations(cooperationMapper.mapOnIds(mappedFrom.getCooperation()))
				.withMovies(movieMapper.mapOnIds(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public StudioEntity mapOnEntity(StudioTO mappedFrom){
		if(mappedFrom == null){
			return null;
		}
		StudioEntity mappedOn = StudioEntity.newBuilder()
				.withId(mappedFrom.getId())
				.withVersion(mappedFrom.getVersion())
				.withCountry(mappedFrom.getCountry())
				.withName(mappedFrom.getName())
				.withCooperations(cooperationMapper.mapOnEntities(mappedFrom.getCooperations()))
				.withMovies(movieMapper.mapOnEntities(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public StudioEntity mapOnEntityFromId(Long studio){
		if(studio == null){
			return null;
		}
		StudioEntity mappedOn = em.getReference(StudioEntity.class, studio);
		return mappedOn;
	}
	
}
