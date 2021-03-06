package com.capgemini.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.MovieDao;
import com.capgemini.dao.StudioDao;
import com.capgemini.mappers.StudioMapper;
import com.capgemini.service.StudioService;
import com.capgemini.types.StudioTO;
import com.capgemini.types.StudioWithNumberOfMoviesTO;
import com.querydsl.core.Tuple;

@Service
public class StudioServiceImpl implements StudioService {
	
	private StudioDao studioDao;
	private MovieDao movieDao;
	private StudioMapper studioMapper;
	
	@Autowired
	public StudioServiceImpl(StudioDao studioDao, StudioMapper studioMapper, MovieDao movieDao) {
		this.studioDao = studioDao;
		this.studioMapper = studioMapper;
		this.movieDao = movieDao;
	}

	@Override
	public void addStudio(StudioTO studio) {
		studioDao.save(studioMapper.mapOnEntity(studio));
	}

	@Override
	public List<StudioWithNumberOfMoviesTO> calculeteNumberOfStudiosMoviesInGivenYear(int year) {
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year, 12, 31);
		return movieDao.findNumerOfEachStudioMoviesInGivenPeriod(startDate, endDate);
	}

	@Override
	public void updateStudio(StudioTO studio) {
		if (studio.getVersion() != studioDao.findOne(studio.getId()).getVersion()) {
			throw new OptimisticLockException();
		}
		studioDao.save(studioMapper.mapOnEntity(studio));
	}

	@Override
	public void deleteStudio(Long studio) {
		studioDao.delete(studio);
	}

}
