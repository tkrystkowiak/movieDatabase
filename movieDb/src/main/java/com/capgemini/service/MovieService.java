package com.capgemini.service;

import java.text.ParseException;

import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.types.MovieTO;

public interface MovieService {
	
	void addMovie(MovieTO movie) throws InvalidDataException, ParseException;
	
}
