package com.capgemini.service;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.CooperationTO;

public interface CooperationService {

	void add(CooperationTO cooperation) throws InvalidDataException;

}
