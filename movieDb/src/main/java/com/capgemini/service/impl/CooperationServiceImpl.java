package com.capgemini.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.CooperationDao;
import com.capgemini.dao.MovieDao;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.mappers.CooperationMapper;
import com.capgemini.service.CooperationService;
import com.capgemini.types.CooperationTO;

@Service
public class CooperationServiceImpl implements CooperationService {
	
	private final CooperationDao cooperationDao;
	private final CooperationMapper cooperationMapper;
	private final MovieDao movieDao;
	
	
	@Autowired
	public CooperationServiceImpl(CooperationDao cooperationDao, CooperationMapper cooperationMapper,MovieDao movieDao) {
		this.cooperationDao = cooperationDao;
		this.cooperationMapper = cooperationMapper;
		this.movieDao = movieDao;
	}
	
	@Override
	public void add(CooperationTO cooperation) throws InvalidDataException{
		
		
			List<CooperationEntity> actorCoops = cooperationDao.findByPeriod(cooperation.getEffectiveDate(),
					cooperation.getExpirationDate());
			if (actorCoops.size() > 0) {
				throw new InvalidDataException("Actor can have only one cooperation in given period of time");
			}
			List<MovieEntity> moviesWithInvalidStudio = movieDao.findByActorAndPeriodAndDifferentStudio(
					cooperation.getActor(), cooperation.getStudio(), cooperation.getEffectiveDate(), cooperation.getExpirationDate());
			if (!moviesWithInvalidStudio.isEmpty()) {
				throw new InvalidDataException("During the cooperation actor can play only for given studio");
			}
		
		cooperationDao.save(cooperationMapper.mapOnEntity(cooperation));
	} 
	
}
