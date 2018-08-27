package com.capgemini.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.ActorDao;
import com.capgemini.dao.CooperationDao;
import com.capgemini.dao.MovieDao;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.mappers.ActorMapper;
import com.capgemini.mappers.MovieMapper;
import com.capgemini.service.ActorService;
import com.capgemini.types.ActorTO;
import com.capgemini.types.MovieTO;

@Service
public class ActorServiceImpl implements ActorService {

	private MovieDao movieDao;
	private CooperationDao cooperationDao;
	private ActorDao actorDao;
	private ActorMapper actorMapper;

	@Autowired
	public ActorServiceImpl(MovieDao movieDao, CooperationDao cooperationDao, ActorDao actorDao,
			ActorMapper actorMapper) {
		this.movieDao = movieDao;
		this.cooperationDao = cooperationDao;
		this.actorDao = actorDao;
		this.actorMapper = actorMapper;
	}

	public void addActor(ActorTO actor) throws InvalidDataException {

		List<Long> movies = actor.getMovies();

		if (movies != null) {
			for (Long movieId : movies) {
				MovieEntity movie = movieDao.findOne(movieId);
				
				List<MovieEntity> actorMovies = movieDao.findMoviesByPeriodWithMatchingIds(movies, movie.getDateOfPremiere(),
						movie.getDateOfPremiere().minusYears(3));
				if (actorMovies.size() > 3) {
					throw new InvalidDataException("Too many movies in given period");
				}
			}
		}

		if (actor.getCooperations() != null) {
			throw new InvalidDataException("You need to add actor first to assign cooperations");
		}
		
		actorDao.save(actorMapper.mapOnEntity(actor));

	}

	@Override
	public List<ActorTO> findActorsWhoDidntActInGivenPeriod(LocalDate startDate, LocalDate endDate) {
		return actorMapper.mapOnTOs(actorDao.findActorsNotPlayingInGivenPeriod(startDate, endDate));
	}

}
