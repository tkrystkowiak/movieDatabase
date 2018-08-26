package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.types.ActorTO;
import com.capgemini.types.MovieTO;

@Component
public class ActorMapper {
	
	@PersistenceContext
	EntityManager em;
	
	private MovieMapper movieMapper;
	private CooperationMapper cooperationMapper;
	
	@Autowired
	public ActorMapper(MovieMapper movieMapper, CooperationMapper cooperationMapper) {
		this.movieMapper = movieMapper;
		this.cooperationMapper = cooperationMapper;
	}

	public ActorTO mapOnTO(ActorEntity mappedFrom){
		ActorTO mappedOn = ActorTO.builder()
				.id(mappedFrom.getId())
				.version(mappedFrom.getVersion())
				.firstName(mappedFrom.getFirstName())
				.lastName(mappedFrom.getLastName())
				.birthDate(mappedFrom.getBirthDate())
				.country(mappedFrom.getCountry())
				.cooperations(cooperationMapper.mapOnIds(mappedFrom.getCooperations()))
				.movies(movieMapper.mapOnIds(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public ActorEntity mapOnEntity(ActorTO mappedFrom){
		ActorEntity mappedOn = ActorEntity.newBuilder()
				.withId(mappedFrom.getId())
				.withVersion(mappedFrom.getVersion())
				.withFirstName(mappedFrom.getFirstName())
				.withLastName(mappedFrom.getLastName())
				.withBirthDate(mappedFrom.getBirthDate())
				.withCountry(mappedFrom.getCountry())
				.withCooperations(cooperationMapper.mapOnEntities(mappedFrom.getCooperations()))
				.withMovies(movieMapper.mapOnEntities(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public List<Long> mapOnIds(List<ActorEntity> entities){
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
	
	public List<ActorTO> mapOnTOs(List<ActorEntity> entities){
		return entities.stream().map(entity -> mapOnTO(entity)).collect(Collectors.toList());
	}
	
	public List<ActorEntity> mapOnEntities(List<Long> ids){
		return ids.stream().map(id -> em.getReference(ActorEntity.class, id)).collect(Collectors.toList());
	}
}
