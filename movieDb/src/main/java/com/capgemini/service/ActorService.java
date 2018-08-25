package com.capgemini.service;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.ActorTO;

public interface ActorService {
	
	void addActor(ActorTO actor) throws InvalidDataException;
	
}
