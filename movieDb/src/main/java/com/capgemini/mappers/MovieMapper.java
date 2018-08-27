package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.types.MovieTO;

@Component
public class MovieMapper {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ActorMapper actorMapper;
	@Autowired
	StudioMapper studioMapper;
	
	
	public MovieTO mapOnTO(MovieEntity mappedFrom){
		if(mappedFrom == null){
			return null;
		}
		
		MovieTO mappedOn = MovieTO.newBuilder()
				.withId(mappedFrom.getId())
				.withGenre(mappedFrom.getGenre())
				.withType(mappedFrom.getType())
				.withTitle(mappedFrom.getTitle())
				.withCountry(mappedFrom.getCountry())
				.withDateOfPremiere(mappedFrom.getDateOfPremiere())
				.withFirstWeekRevenue(mappedFrom.getFirstWeekRevenue())
				.withTotalRevenue(mappedFrom.getTotalRevenue())
				.withBudget(mappedFrom.getBudget())
				.withThreeD(mappedFrom.getThreeD())
				.withLength(mappedFrom.getLength())
				.withCast(actorMapper.mapOnIds(mappedFrom.getCast()))
				.withStudio(mappedFrom.getStudio().getId())
				.build();
		return mappedOn;
	}
	
	public MovieEntity mapOnEntity(MovieTO mappedFrom){
		if(mappedFrom == null){
			return null;
		}
		
		MovieEntity mappedOn = MovieEntity.newBuilder()
				.withId(mappedFrom.getId())
				.withGenre(mappedFrom.getGenre())
				.withType(mappedFrom.getType())
				.withTitle(mappedFrom.getTitle())
				.withCountry(mappedFrom.getCountry())
				.withDateOfPremiere(mappedFrom.getDateOfPremiere())
				.withFirstWeekRevenue(mappedFrom.getFirstWeekRevenue())
				.withTotalRevenue(mappedFrom.getTotalRevenue())
				.withBudget(mappedFrom.getBudget())
				.withThreeD(mappedFrom.getThreeD())
				.withLength(mappedFrom.getLength())
				.withCast(actorMapper.mapOnEntities(mappedFrom.getCast()))
				.withStudio(studioMapper.mapOnEntityFromId(mappedFrom.getStudio()))
				.build();
		return mappedOn;
	}
	
	public List<Long> mapOnIds(List<MovieEntity> entities){
		if(entities == null){
			return null;
		}
		
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
	
	public List<MovieTO> mapOnTOs(List<MovieEntity> entities){
		if(entities == null){
			return null;
		}
		return entities.stream().map(entity -> mapOnTO(entity)).collect(Collectors.toList());
	}
	
	public List<MovieEntity> mapOnEntities(List<Long> ids){
		if(ids == null){
			return null;
		}
		return ids.stream().map(id -> em.getReference(MovieEntity.class, id)).collect(Collectors.toList());
	}
}
