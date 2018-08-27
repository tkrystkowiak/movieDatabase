package com.capgemini.types;

public class StudioWithNumberOfMoviesTO {
	
	private String name;
	
	private long numberOfMovies;

	public StudioWithNumberOfMoviesTO(String name, long numberOfMovies) {
		this.name = name;
		this.numberOfMovies = numberOfMovies;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumberOfMovies() {
		return numberOfMovies;
	}

	public void setNumberOfMovies(long numberOfMovies) {
		this.numberOfMovies = numberOfMovies;
	}
	
}
