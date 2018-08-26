package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.domain.ActorEntity;

public interface CustomActorDao {

	List<ActorEntity> findActorsNotPlayingInGivenPeriod(LocalDate startDate, LocalDate endDate);

}
