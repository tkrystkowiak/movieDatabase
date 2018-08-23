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
	ActorMapper actorMapper;
	
	@Autowired
	MovieMapper movieMapper;
	
	public StudioTO mapOnTO(StudioEntity mappedFrom){
		StudioTO mappedOn = StudioTO.builder()
				.id(mappedFrom.getId())
				.country(mappedFrom.getCountry())
				.name(mappedFrom.getName())
				.actors(actorMapper.mapOnIds(mappedFrom.getActors()))
				.movies(movieMapper.mapOnIds(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public StudioEntity mapOnEntity(StudioTO mappedFrom){
		StudioEntity mappedOn = StudioEntity.builder()
				.id(mappedFrom.getId())
				.country(mappedFrom.getCountry())
				.name(mappedFrom.getName())
				.actors(actorMapper.mapOnEntities(mappedFrom.getActors()))
				.movies(movieMapper.mapOnEntities(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
}
