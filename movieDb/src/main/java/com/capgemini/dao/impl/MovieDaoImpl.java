package com.capgemini.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CustomMovieDao;
import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.QMovieEntity;
import com.capgemini.domain.SearchCriteria;
import com.capgemini.domain.StudioEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MovieDaoImpl implements CustomMovieDao {

	@PersistenceContext
	private EntityManager entityManager;

	private QMovieEntity movie = QMovieEntity.movieEntity;

	private JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

	@Override
	public List<MovieEntity> findByCriteria(SearchCriteria searchCriteria) {
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		query.select(movie).from(movie);
		if (searchCriteria.getType() != null) {
			query.where(movie.type.eq(searchCriteria.getType()));
		}
		if (searchCriteria.getGenre() != null) {
			query.where(movie.genre.eq(searchCriteria.getGenre()));
		}
		if (searchCriteria.getMinLength() != null) {
			query.where(movie.length.goe(searchCriteria.getMinLength()));
		}
		if (searchCriteria.getMaxLength() != null) {
			query.where(movie.length.loe(searchCriteria.getMaxLength()));
		}
		if (searchCriteria.getMinDateOfPremiere() != null) {
			query.where(movie.dateOfPremiere.goe(searchCriteria.getMinDateOfPremiere()));
		}
		if (searchCriteria.getMaxDateOfPremiere() != null) {
			query.where(movie.dateOfPremiere.loe(searchCriteria.getMaxDateOfPremiere()));
		}
		if (searchCriteria.getStudioId() != null) {
			StudioEntity studio = entityManager.getReference(StudioEntity.class, searchCriteria.getStudioId());
			query.where(movie.studio.eq(studio));
		}
		if (searchCriteria.getThreeD() != null) {
			query.where(movie.threeD.eq(searchCriteria.getThreeD()));
		}
		return query.fetch();
	}

	@Override
	public List<MovieEntity> findWithQueryDSL(String title) {
		return queryFactory.select(movie).from(movie).where(movie.title.eq("Matrix")).fetch();
	}

	@Override
	public List<MovieEntity> findByActorAndPeriod(Long actorId, LocalDate startDate, LocalDate endDate) {
		ActorEntity actor = entityManager.getReference(ActorEntity.class, actorId);
		return queryFactory.select(movie).from(movie)
				.where(movie.cast.contains(actor).and(movie.dateOfPremiere.between(endDate, startDate))).fetch();
	}

	public List<MovieEntity> findByActorAndPeriodAndDifferentStudio(Long actorId, Long studioId, LocalDate startDate,
			LocalDate endDate) {
		ActorEntity actor = entityManager.getReference(ActorEntity.class, actorId);
		StudioEntity studio = entityManager.getReference(StudioEntity.class, studioId);
		return queryFactory.select(movie).from(movie).where(movie.cast.contains(actor)
				.and(movie.dateOfPremiere.between(endDate, startDate)).and(movie.studio.ne(studio))).fetch();
	}

	public Integer findAverageFirstWeekRevenue() {
		return queryFactory.select(movie.firstWeekRevenue.avg()).from(movie).fetchOne().intValue();
	}

	public Integer findAverageTotalRevenue() {
		return queryFactory.select(movie.totalRevenue.avg()).from(movie).fetchOne().intValue();
	}

	public Long findCombinedRevenueOfTopSellingMovies(int numberOfMovies) {
		return queryFactory.select(movie.totalRevenue.sum()).from(movie).where(movie.totalRevenue.in(JPAExpressions
				.select(movie.totalRevenue).from(movie).limit(numberOfMovies).orderBy(movie.totalRevenue.desc())))
				.fetchCount();
	}

	public Long findCobinedBudgetOfFilmsInGivenPeriod(LocalDate startDate, LocalDate endDate) {
		return queryFactory.select(movie.budget.sum()).from(movie)
				.where(movie.dateOfPremiere.between(endDate, startDate)).fetchCount();
	}

	public List<MovieEntity> findLongestMovieWithGivenStudioAndPeriod(Long studioId, LocalDate startDate,
			LocalDate endDate) {
		StudioEntity studio = entityManager.getReference(StudioEntity.class, studioId);
		return queryFactory.select(movie).from(movie)
				.where(movie.dateOfPremiere.between(endDate, startDate).and(movie.studio.eq(studio))
						.and(movie.length.eq((JPAExpressions.select(movie.length.max()).from(movie).where(
								movie.dateOfPremiere.between(endDate, startDate).and(movie.studio.eq(studio)))))))
				.fetch();
	}
	
	public List<Tuple> findNumerOfEachStudioMoviesInGivenPeriod(LocalDate startDate, LocalDate endDate){
		return queryFactory.select(movie.studio,movie.count())
				.from(movie)
				.where(movie.dateOfPremiere.between(endDate, startDate))
				.groupBy(movie.studio)
				.fetch();	
	}

}