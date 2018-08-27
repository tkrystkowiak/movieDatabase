package com.capgemini.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.MovieDao;
import com.capgemini.dao.StudioDao;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.service.impl.MovieServiceImpl;
import com.capgemini.service.impl.StudioServiceImpl;
import com.capgemini.types.StudioTO;
import com.capgemini.types.StudioWithNumberOfMoviesTO;
import com.querydsl.core.Tuple;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class StudioServiceTest {

	@PersistenceContext
	EntityManager em;

	@Autowired
	StudioServiceImpl studioService;

	@Autowired
	MovieDao movieDao;

	@Autowired
	StudioDao studioDao;

	@Test
	public void shouldAddStudioToDatabase() {
		//given
		StudioTO studio = StudioTO.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		//when
		studioService.addStudio(studio);
		//then
		List<StudioEntity> result = studioDao.findAll();
		assertEquals(1,result.size());
		assertEquals("Super",result.get(0).getName());

	}
	
	@Test
	public void shouldAddThreeStudiosToDatabase() {
		//given
		StudioTO studio1 = StudioTO.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		StudioTO studio2 = StudioTO.newBuilder()
				.withName("Stare")
				.withCountry("Polska")
				.build();
		
		StudioTO studio3 = StudioTO.newBuilder()
				.withName("Nowe")
				.withCountry("Polska")
				.build();
		//when
		studioService.addStudio(studio1);
		studioService.addStudio(studio2);
		studioService.addStudio(studio3);
		//then
		List<StudioEntity> result = studioDao.findAll();
		assertEquals(3,result.size());
		assertEquals("Super",result.get(0).getName());
		assertEquals("Stare",result.get(1).getName());
		assertEquals("Nowe",result.get(2).getName());

	}
	
	@Test
	public void testShouldCalculateNumberOfMoviesPerStudioIngGivenYear() throws InvalidDataException, ParseException{
		//given
		StudioEntity studio1 = StudioEntity.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		StudioEntity studio2 = StudioEntity.newBuilder()
				.withName("Stare")
				.withCountry("Polska")
				.build();
		
		StudioEntity studio3 = StudioEntity.newBuilder()
				.withName("Nowe")
				.withCountry("Polska")
				.build();
		studioDao.save(studio1);
		studioDao.save(studio2);
		studioDao.save(studio3);
		
		movieDao.save(generateSampleMovieWithStudioAndPremiere(studio1, LocalDate.of(2018,03,10)));
		movieDao.save(generateSampleMovieWithStudioAndPremiere(studio2, LocalDate.of(2018,04,10)));
		movieDao.save(generateSampleMovieWithStudioAndPremiere(studio3, LocalDate.of(2018,05,10)));
		
		//when
		
		List<StudioWithNumberOfMoviesTO> result = studioService.calculeteNumberOfStudiosMoviesInGivenYear(2018);
		//then
		assertEquals(3,result.size());
		assertEquals("Super",result.get(0).getName());
		assertEquals(1,result.get(0).getNumberOfMovies());
		assertEquals("Stare",result.get(1).getName());
		assertEquals(1,result.get(1).getNumberOfMovies());
		assertEquals("Nowe",result.get(2).getName());
		assertEquals(1,result.get(2).getNumberOfMovies());
	}
	
	@Test
	public void testShouldCalculateNumberOfMoviesPerStudioNotIncludingMoviesFromOtherYears() throws InvalidDataException, ParseException{
		//given
		StudioEntity studio1 = StudioEntity.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		StudioEntity studio2 = StudioEntity.newBuilder()
				.withName("Stare")
				.withCountry("Polska")
				.build();
		
		StudioEntity studio3 = StudioEntity.newBuilder()
				.withName("Nowe")
				.withCountry("Polska")
				.build();
		
		studioDao.save(studio1);
		studioDao.save(studio2);
		studioDao.save(studio3);
		
		movieDao.save(generateSampleMovieWithStudioAndPremiere(studio1, LocalDate.of(2018,03,10)));
		movieDao.save(generateSampleMovieWithStudioAndPremiere(studio2, LocalDate.of(2017,04,10)));
		movieDao.save(generateSampleMovieWithStudioAndPremiere(studio3, LocalDate.of(2017,05,10)));
		movieDao.save(generateSampleMovieWithStudioAndPremiere(studio1, LocalDate.of(2018,05,10)));
		
		//when
		
		List<StudioWithNumberOfMoviesTO> result = studioService.calculeteNumberOfStudiosMoviesInGivenYear(2018);
		//then
		assertEquals(1,result.size());
		assertEquals("Super",result.get(0).getName());
		assertEquals(2,result.get(0).getNumberOfMovies());
	}
	
	public MovieEntity generateSampleMovieWithStudioAndPremiere(StudioEntity studio, LocalDate premiere){
		return MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(premiere)
				.withFirstWeekRevenue(1)
				.withTotalRevenue(3)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.withStudio(studio)
				.build();
	}
	
}
