package com.capgemini.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.QActorEntity;
import com.capgemini.domain.QMovieEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ActorDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private QActorEntity actor = QActorEntity.actorEntity;
	private QMovieEntity movie = QMovieEntity.movieEntity;
	
	private JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
	
	public List<ActorEntity> findActorsNotPlayingInGivenPeriod(LocalDate startDate, LocalDate endDate){
		return queryFactory.select(actor)
				.from(actor)
				.leftJoin(movie)
				.where(movie.dateOfPremiere.notBetween(endDate, startDate))
				.fetch();
	}
	
}
