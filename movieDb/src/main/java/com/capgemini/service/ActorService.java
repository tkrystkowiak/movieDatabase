package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.ActorTO;

public interface ActorService {
	
	void addActor(ActorTO actor) throws InvalidDataException;
	
	List<ActorTO> findActorsWhoDidntActInGivenPeriod(LocalDate startDate,LocalDate endDate);
}
