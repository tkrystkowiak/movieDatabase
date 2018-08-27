package com.capgemini.service;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.CooperationTO;

/**
 * This service handles requests concerning cooperations
 * 
 * @author tkrystko
 *
 */
public interface CooperationService {

	/**
	 * Adds new cooperation to database
	 * 
	 * @param cooperation
	 * @throws InvalidDataException
	 */
	void add(CooperationTO cooperation) throws InvalidDataException;
	
	/**
	 * Updates cooperation in database
	 * 
	 * @param cooperation
	 * @throws InvalidDataException
	 */
	void update(CooperationTO cooperation) throws InvalidDataException;
	
	/**
	 * Deletes cooperation from database
	 * 
	 * @param coopId
	 */
	void delete(Long coopId);

}
