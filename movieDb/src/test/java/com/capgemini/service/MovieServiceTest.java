package com.capgemini.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
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
import com.capgemini.types.MovieTO;
import com.capgemini.types.SearchCriteria;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class MovieServiceTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MovieServiceImpl movieService;
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	StudioDao studioDao;
	
	@Test
	public void testShouldAddOneMovieToDatabase() throws InvalidDataException, ParseException{
		//when
		movieService.addMovie(generateSampleMovieWithTitle("Matrix"));
		List<MovieEntity> result = movieDao.findAll();
		//then
		assertEquals(1,result.size());
		assertEquals("Matrix",result.get(0).getTitle());
	}
	
	@Test
	public void testShouldAddThreeMoviesToDatabase() throws InvalidDataException, ParseException{
		//when
		movieService.addMovie(generateSampleMovieWithTitle("Matrix"));
		movieService.addMovie(generateSampleMovieWithTitle("Figth Club"));
		movieService.addMovie(generateSampleMovieWithTitle("Inception"));
		List<MovieEntity> result = movieDao.findAll();
		//then
		assertEquals(3,result.size());
		assertEquals("Matrix",result.get(0).getTitle());
		assertEquals("Figth Club",result.get(1).getTitle());
		assertEquals("Inception",result.get(2).getTitle());
	}
	
	@Test
	public void testShouldThrowThreeMoviesToDatabase() throws InvalidDataException, ParseException{
		//when
		movieService.addMovie(generateSampleMovieWithTitle("Matrix"));
		movieService.addMovie(generateSampleMovieWithTitle("Figth Club"));
		movieService.addMovie(generateSampleMovieWithTitle("Inception"));
		List<MovieEntity> result = movieDao.findAll();
		//then
		assertEquals(3,result.size());
		assertEquals("Matrix",result.get(0).getTitle());
		assertEquals("Figth Club",result.get(1).getTitle());
		assertEquals("Inception",result.get(2).getTitle());
	}
	
	@Test
	public void testShouldThrowExceptionWhenFirstWeekRevenueBiggerThanTotal() throws InvalidDataException, ParseException{
		//given
		MovieTO toSave = MovieTO.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(6)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.build();
		boolean thrown = false;
		//when
		try{
		movieService.addMovie(toSave);
		}
		catch (InvalidDataException e) {
			if (e.getMessage().equals("First week revenue cannot be bigger than total")) {
				thrown = true;
			}
		}
		//then
		assertTrue(thrown);
	}
	
	@Test
	public void testShouldThrowExceptionWhenMovieWithSimiliarDataExist() throws InvalidDataException, ParseException{
		//given
		MovieTO toSave = MovieTO.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(3)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.build();
		boolean thrown = false;
		//when
		movieService.addMovie(toSave);
		try{
		movieService.addMovie(toSave);
		}
		catch (InvalidDataException e) {
			if (e.getMessage().equals("There can be only one movie with identical title, country and date of premiere")) {
				thrown = true;
			}
		}
		//then
		assertTrue(thrown);
	}
	
	@Test
	public void testShouldFindMovieByCriteria() throws InvalidDataException, ParseException{
		//given
		StudioEntity studio = StudioEntity.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		studio = studioDao.save(studio);
		
		MovieEntity movie = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.withStudio(studio)
				.build();
		movie = movieDao.save(movie);

		SearchCriteria criteria = SearchCriteria.newBuilder()
				.withType("Technicolor")
				.withGenre("Sci-Fi")
				.withThreeD(false)
				.withMinLength(100)
				.withMaxLength(150)
				.withMinDateOfPremiere(LocalDate.of(2018,03,8))
				.withMaxDateOfPremiere(LocalDate.of(2018,03,15))
				.withStudio(studio.getId())
				.build();
		//when
		List<MovieTO> result = movieService.findMovieByCriteria(criteria);
		//then
		assertEquals(1,result.size());
		assertEquals("Matrix",result.get(0).getTitle());
	}
	
	@Test
	public void testShouldCalculateAverageFirstWeekRevenue() throws InvalidDataException, ParseException{
		//given
		movieDao.save(generateSampleMovieWithFirstWekRevenue(2));
		movieDao.save(generateSampleMovieWithFirstWekRevenue(3));
		movieDao.save(generateSampleMovieWithFirstWekRevenue(4));
		//when
		double result = movieService.calculateAverageFirstWeekRevenue();
		//then
		assertEquals(3.0,result,0.001);
	}
	
	@Test
	public void testShouldCalculateAverageTotalRevenue() throws InvalidDataException, ParseException{
		//given
		movieDao.save(generateSampleMovieWithTotalRevenue(2));
		movieDao.save(generateSampleMovieWithTotalRevenue(3));
		movieDao.save(generateSampleMovieWithTotalRevenue(4));
		//when
		double result = movieService.calculateAverageTotalRevenue();
		//then
		assertEquals(3.0,result,0.001);
	}
	
	@Test
	public void testShouldCalculateCombinedRevenueOfTopExpensiveMovies(){
		//given
		movieDao.save(generateSampleMovieWithTotalRevenueAndBudget(2,5));
		movieDao.save(generateSampleMovieWithTotalRevenueAndBudget(3,4));
		movieDao.save(generateSampleMovieWithTotalRevenueAndBudget(5,3));
		movieDao.save(generateSampleMovieWithTotalRevenueAndBudget(4,2));
		//when
		long result = movieService.calculateCombinedRevenueOfTopExpepensiveMovies(3);
		//then
		assertEquals(10,result);
	}
	
	@Test
	public void testShouldCalculateCombinedMoviesBudgetInGivenPeriod(){
		//given
		movieDao.save(generateSampleMovieWithBudgetAndPremiere(5,LocalDate.of(2018, 05, 20)));
		movieDao.save(generateSampleMovieWithBudgetAndPremiere(4,LocalDate.of(2018, 05, 22)));
		movieDao.save(generateSampleMovieWithBudgetAndPremiere(3,LocalDate.of(2018, 05, 24)));
		movieDao.save(generateSampleMovieWithBudgetAndPremiere(2,LocalDate.of(2018, 05, 30)));
		//when
		long result = movieService.calculateCombinedMoviesBudgetInGivenPeriod(LocalDate.of(2018, 05, 20),LocalDate.of(2018, 05, 24));
		//then
		assertEquals(12,result);
	}
	
	@Test
	public void testShouldFindLongestMoviesByStudioAndPeriod(){
		//given
		StudioEntity studio = StudioEntity.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		long studioId = studioDao.save(studio).getId();
		
		MovieEntity movie1 = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.withStudio(studio)
				.build();
		
		MovieEntity movie2 = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Blade Runner")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,11))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(150)
				.withStudio(studio)
				.build();
		
		movieDao.save(movie1);
		movieDao.save(movie2);
		//when
		List<MovieTO> result = movieService.findLongestMoviesByGivenStudioInGivenPeriod(studioId,LocalDate.of(2018, 03, 10),LocalDate.of(2018, 03, 11));
		//then
		assertEquals(1,result.size());
		assertEquals("Blade Runner",result.get(0).getTitle());
	}
	
	@Test
	public void testShouldTHrowOptimisticLockingWhenVersionMismatch() throws InvalidDataException{
		//given
		
		MovieEntity movie1 = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.build();
		
		Long movieId = movieDao.save(movie1).getId();
		
		MovieTO movie2 = MovieTO.newBuilder()
				.withId(movieId)
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Blade Runner")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,11))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(150)
				.build();
		boolean thrown = false;
		//when
		try{
			movieService.updateMovie(movie2);
		}
		catch(OptimisticLockException e){
			thrown = true;
		}
		//then
		assertTrue(thrown);
	}
	
	MovieTO toSave = MovieTO.newBuilder()
			.withGenre("Sci-Fi")
			.withType("Technicolor")
			.withTitle("Matrix")
			.withCountry("Polska")
			.withDateOfPremiere(LocalDate.of(2018,03,10))
			.withFirstWeekRevenue(6)
			.withTotalRevenue(5)
			.withBudget(3)
			.withThreeD(false)
			.withLength(120)
			.build();
	
	public MovieEntity generateSampleMovieWithBudgetAndPremiere(int budget, LocalDate premiere){
		return MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(premiere)
				.withFirstWeekRevenue(1)
				.withTotalRevenue(3)
				.withBudget(budget)
				.withThreeD(false)
				.withLength(120)
				.build();
	}
	
	public MovieTO generateSampleMovieWithTitle(String title){
		return MovieTO.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle(title)
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.build();
	}
	
	public MovieEntity generateSampleMovieWithFirstWekRevenue(int revenue){
		return MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(revenue)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.build();
	}
	
	public MovieEntity generateSampleMovieWithTotalRevenue(int revenue){
		return MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(1)
				.withTotalRevenue(revenue)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.build();
	}
	
	public MovieEntity generateSampleMovieWithTotalRevenueAndBudget(int revenue,int budget){
		return MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(2018,03,10))
				.withFirstWeekRevenue(1)
				.withTotalRevenue(revenue)
				.withBudget(budget)
				.withThreeD(false)
				.withLength(120)
				.build();
	}
	
}
