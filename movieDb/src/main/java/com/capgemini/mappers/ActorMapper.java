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
	
	private CooperationMapper cooperationMapper;
	
	@Autowired
	public ActorMapper(CooperationMapper cooperationMapper) {
		this.cooperationMapper = cooperationMapper;
	}

	public ActorTO mapOnTO(ActorEntity mappedFrom){
		if(mappedFrom == null){
			return null;
		}
		ActorTO mappedOn = ActorTO.newBuilder()
				.withId(mappedFrom.getId())
				.withVersion(mappedFrom.getVersion())
				.withFirstName(mappedFrom.getFirstName())
				.withLastName(mappedFrom.getLastName())
				.withBirthDate(mappedFrom.getBirthDate())
				.withCountry(mappedFrom.getCountry())
				.withCooperations(cooperationMapper.mapOnIds(mappedFrom.getCooperations()))
				.withMovies(mapMovieEntitiesOnIds(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public ActorEntity mapOnEntity(ActorTO mappedFrom){
		if(mappedFrom == null){
			return null;
		}
		ActorEntity mappedOn = ActorEntity.newBuilder()
				.withId(mappedFrom.getId())
				.withVersion(mappedFrom.getVersion())
				.withFirstName(mappedFrom.getFirstName())
				.withLastName(mappedFrom.getLastName())
				.withBirthDate(mappedFrom.getBirthDate())
				.withCountry(mappedFrom.getCountry())
				.withCooperations(cooperationMapper.mapOnEntities(mappedFrom.getCooperations()))
				.withMovies(mapMovieIdsOnEntities(mappedFrom.getMovies()))
				.build();
		return mappedOn;
	}
	
	public List<Long> mapOnIds(List<ActorEntity> entities){
		if(entities == null){
			return null;
		}
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
	
	public List<ActorTO> mapOnTOs(List<ActorEntity> entities){
		if(entities == null){
			return null;
		}
		return entities.stream().map(entity -> mapOnTO(entity)).collect(Collectors.toList());
	}
	
	public List<ActorEntity> mapOnEntities(List<Long> ids){
		if(ids == null){
			return null;
		}
		return ids.stream().map(id -> em.getReference(ActorEntity.class, id)).collect(Collectors.toList());
	}
	
	private List<MovieEntity> mapMovieIdsOnEntities(List<Long> ids){
		if(ids == null){
			return null;
		}
		return ids.stream().map(id -> em.getReference(MovieEntity.class, id)).collect(Collectors.toList());
	}
	
	private List<Long> mapMovieEntitiesOnIds(List<MovieEntity> entities){
		if(entities == null){
			return null;
		}
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
}
