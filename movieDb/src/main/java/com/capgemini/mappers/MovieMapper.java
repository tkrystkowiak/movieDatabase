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
	
	public MovieTO mapOnTO(MovieEntity mappedFrom){
		MovieTO mappedOn = MovieTO.builder()
				.id(mappedFrom.getId())
				.genre(mappedFrom.getGenre())
				.type(mappedFrom.getType())
				.title(mappedFrom.getTitle())
				.dateOfPremiere(mappedFrom.getDateOfPremiere())
				.firstWeekRevenue(mappedFrom.getFirstWeekRevenue())
				.totalRevenue(mappedFrom.getTotalRevenue())
				.budget(mappedFrom.getBudget())
				.is3D(mappedFrom.getThreeD())
				.length(mappedFrom.getLength())
				.cast(actorMapper.mapOnIds(mappedFrom.getCast()))
				.studio(mappedFrom.getStudio().getId())
				.build();
		return mappedOn;
	}
	
	public MovieEntity mapOnEntity(MovieTO mappedFrom){
		MovieEntity mappedOn = MovieEntity.newBuilder()
				.withId(mappedFrom.getId())
				.withGenre(mappedFrom.getGenre())
				.withType(mappedFrom.getType())
				.withTitle(mappedFrom.getTitle())
				.withDateOfPremiere(mappedFrom.getDateOfPremiere())
				.withFirstWeekRevenue(mappedFrom.getFirstWeekRevenue())
				.withTotalRevenue(mappedFrom.getTotalRevenue())
				.withBudget(mappedFrom.getBudget())
				.withIs3D(mappedFrom.getIs3D())
				.withLength(mappedFrom.getLength())
				.withCast(actorMapper.mapOnEntities(mappedFrom.getCast()))
				.withStudio(em.getReference(StudioEntity.class, mappedFrom.getStudio()))
				.build();
		return mappedOn;
	}
	
	public List<Long> mapOnIds(List<MovieEntity> entities){
		return entities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
	}
	
	public List<MovieTO> mapOnTOs(List<MovieEntity> entities){
		return entities.stream().map(entity -> mapOnTO(entity)).collect(Collectors.toList());
	}
	
	public List<MovieEntity> mapOnEntities(List<Long> ids){
		return ids.stream().map(id -> em.getReference(MovieEntity.class, id)).collect(Collectors.toList());
	}
}
