package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.types.ActorTO;

@Component
public class ActorMapper {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MovieMapper movieMapper;
	
	public ActorTO mapOnTO(ActorEntity mappedFrom){
		ActorTO mappedOn = ActorTO.builder()
				.id(mappedFrom.getId())
				.firstName(mappedFrom.getFirstName())
				.lastName(mappedFrom.getLastName())
				.birthDate(mappedFrom.getBirthDate())
				.country(mappedFrom.getCountry())
				.studio(mappedFrom.getStudio().getId())
				.movies(movieMapper.mapOnIds(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public ActorEntity mapOnEntity(ActorTO mappedFrom){
		ActorEntity mappedOn = ActorEntity.builder()
				.id(mappedFrom.getId())
				.firstName(mappedFrom.getFirstName())
				.lastName(mappedFrom.getLastName())
				.birthDate(mappedFrom.getBirthDate())
				.country(mappedFrom.getCountry())
				.studio(em.getReference(StudioEntity.class, mappedFrom.getStudio()))
				.movies(movieMapper.mapOnEntities(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public List<Long> mapOnIds(List<ActorEntity> entities){
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
	
	public List<ActorEntity> mapOnEntities(List<Long> ids){
		return ids.stream().map(id -> em.getReference(ActorEntity.class, id)).collect(Collectors.toList());
	}
}
