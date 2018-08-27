package com.capgemini.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.dao.CustomActorDao;
import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.QActorEntity;
import com.capgemini.domain.QMovieEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ActorDaoImpl implements CustomActorDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private QActorEntity actor = QActorEntity.actorEntity;
	private QMovieEntity movie = QMovieEntity.movieEntity;
	
	@Override
	public List<ActorEntity> findActorsNotPlayingInGivenPeriod(LocalDate startDate, LocalDate endDate){
		JPAQuery<ActorEntity> query = new JPAQuery<ActorEntity>(entityManager);
		return query.select(actor)
				.from(actor)
				.leftJoin(actor.movies,movie)
				.where(movie.dateOfPremiere.notBetween(startDate,endDate)
						.or(actor.movies.isEmpty()))
				.fetch();
	}
	
}
