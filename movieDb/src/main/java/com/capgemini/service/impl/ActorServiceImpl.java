package com.capgemini.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CooperationDao;
import com.capgemini.dao.MovieDao;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.mappers.MovieMapper;
import com.capgemini.service.ActorService;
import com.capgemini.types.ActorTO;
import com.capgemini.types.MovieTO;

@Service
public class ActorServiceImpl implements ActorService {

	private MovieDao movieDao;
	private CooperationDao cooperationDao;
	private MovieMapper movieMapper;

	@Autowired
	public ActorServiceImpl(MovieDao movieDao, CooperationDao cooperationDao, MovieMapper movieMapper) {
		this.movieDao = movieDao;
		this.cooperationDao = cooperationDao;
		this.movieMapper = movieMapper;
	}

	public void addActor(ActorTO actor) throws InvalidDataException {

		List<Long> movies = actor.getMovies();

		for (Long movieId : movies) {
			MovieEntity movie = movieDao.findOne(movieId);
			List<MovieEntity> actorMovies = movieDao.findByActorAndPeriod(actor.getId(), movie.getDateOfPremiere(),
					movie.getDateOfPremiere().minusYears(3));
			if (actorMovies.size() > 3) {
				throw new InvalidDataException("Too many movies in given period");
			}
		}

		List<Long> cooperations = actor.getMovies();

		for (Long coopId : cooperations) {
			CooperationEntity coop = cooperationDao.findOne(coopId);
			List<CooperationEntity> actorCoops = cooperationDao.findByPeriod(coop.getEffectiveDate(),
					coop.getExpirationDate());
			if (actorCoops.size() > 1) {
				throw new InvalidDataException("Actor can have only one cooperation in given period of time");
			}
			List<MovieEntity> moviesWithInvalidStudio = movieDao.findByActorAndPeriodAndDifferentStudio(actor.getId(),
					coop.getStudio().getId(), coop.getEffectiveDate(), coop.getExpirationDate());
			if (!moviesWithInvalidStudio.isEmpty()) {
				throw new InvalidDataException("During the cooperation actor can play only for given studio");
			}
		}

	}

	@Override
	public List<ActorTO> findActorsWhoDidntActInGivenPeriod(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
