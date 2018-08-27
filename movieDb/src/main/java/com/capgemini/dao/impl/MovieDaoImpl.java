package com.capgemini.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.CustomMovieDao;
import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.QMovieEntity;
import com.capgemini.domain.QStudioEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.types.SearchCriteria;
import com.capgemini.types.StudioWithNumberOfMoviesTO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MovieDaoImpl implements CustomMovieDao {

	@PersistenceContext
	private EntityManager entityManager;

	private QMovieEntity movie = QMovieEntity.movieEntity;
	private QStudioEntity studio = QStudioEntity.studioEntity;

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
	public List<MovieEntity> findMoviesByPeriodWithMatchingIds(List<Long> movieIds, LocalDate startDate, LocalDate endDate) {
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		return query.select(movie).from(movie)
				.where(movie.id.in(movieIds).and(movie.dateOfPremiere.between(endDate, startDate))).fetch();
	}
	
	@Override
	public List<MovieEntity> findByActorAndPeriodAndDifferentStudio(Long actorId, Long studioId, LocalDate startDate,
			LocalDate endDate) {
		ActorEntity actor = entityManager.getReference(ActorEntity.class, actorId);
		StudioEntity studio = entityManager.getReference(StudioEntity.class, studioId);
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		return query.select(movie).from(movie).where(movie.cast.contains(actor)
				.and(movie.dateOfPremiere.between(startDate, endDate)).and(movie.studio.ne(studio))).fetch();
	}
	
	@Override
	public Double findAverageFirstWeekRevenue() {
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		return query.select(movie.firstWeekRevenue.avg()).from(movie).fetchOne();
	}
	
	@Override
	public Double findAverageTotalRevenue() {
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		return query.select(movie.totalRevenue.avg()).from(movie).fetchOne();
	}
	
	@Override
	public Integer findCombinedRevenueOfTopExpensiveMovies(int numberOfMovies) {
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		return query.select(movie.totalRevenue.sum()).from(movie).where(movie.in(findTopExpensiveMovies(numberOfMovies)))
				.fetchOne();
				
	}
	
	@Override
	public Integer findCobinedBudgetOfFilmsInGivenPeriod(LocalDate startDate, LocalDate endDate) {
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		return query.select(movie.budget.sum()).from(movie)
				.where(movie.dateOfPremiere.between(startDate,endDate)).fetchOne();
	}
	
	@Override
	public List<MovieEntity> findLongestMovieWithGivenStudioAndPeriod(Long studioId, LocalDate startDate,
			LocalDate endDate) {
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		StudioEntity studio = entityManager.getReference(StudioEntity.class, studioId);
		return query.select(movie).from(movie)
				.where(movie.dateOfPremiere.between(startDate, endDate).and(movie.studio.eq(studio))
						.and(movie.length.eq((JPAExpressions.select(movie.length.max()).from(movie).where(
								movie.dateOfPremiere.between(startDate, endDate).and(movie.studio.eq(studio)))))))
				.fetch();
	}
	
	
	@Override
	public List<StudioWithNumberOfMoviesTO> findNumerOfEachStudioMoviesInGivenPeriod(LocalDate startDate, LocalDate endDate){
		
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		
		List<Tuple> tuples = query
				.select(movie.studio.name,movie.studio.name.count())
				.from(movie)
				.where(movie.dateOfPremiere.between(startDate, endDate))
				.groupBy(movie.studio.name)
				.fetch();	
		
		List<StudioWithNumberOfMoviesTO> toReturn  = new ArrayList<StudioWithNumberOfMoviesTO>();
		
		tuples.forEach(tuple -> {
			toReturn.add(new StudioWithNumberOfMoviesTO(tuple.get(movie.studio.name),tuple.get(movie.studio.name.count())));
			});
		
		return toReturn;
	}
	
	@Override
	public List<MovieEntity> findTopExpensiveMovies(int numberOfMovies){
		JPAQuery<MovieEntity> query = new JPAQuery<MovieEntity>(entityManager);
		return query.select(movie).from(movie).orderBy(movie.budget.desc()).limit(numberOfMovies)
				.fetch();
	}

}
