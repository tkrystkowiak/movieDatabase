package com.capgemini.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.ActorTO;

/**
 * This service handles requests concerning actors
 * 
 * @author tkrystko
 *
 */
public interface ActorService {

	/**
	 * Adds new actor to database
	 * 
	 * @param actor
	 * @throws InvalidDataException
	 */
	void addActor(ActorTO actor) throws InvalidDataException;

	/**
	 * Updates existing actor in database
	 * 
	 * @param actor
	 * @throws InvalidDataException
	 */
	void updateActor(ActorTO actor) throws InvalidDataException;

	/**
	 * Deletes actor from database
	 * 
	 * @param actor
	 */
	void deleteActor(Long actor);

	/**
	 * @param startDate
	 * @param endDate
	 * @return List of actors who did not act in given period
	 */
	List<ActorTO> findActorsWhoDidntActInGivenPeriod(LocalDate startDate, LocalDate endDate);
}
