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
		StudioTO mappedOn = StudioTO.builder()
				.id(mappedFrom.getId())
				.version(mappedFrom.getVersion())
				.country(mappedFrom.getCountry())
				.name(mappedFrom.getName())
				.cooperations(cooperationMapper.mapOnIds(mappedFrom.getCooperation()))
				.movies(movieMapper.mapOnIds(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public StudioEntity mapOnEntity(StudioTO mappedFrom){
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
	
}
