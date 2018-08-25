package com.capgemini.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.SearchCriteria;
import com.capgemini.domain.StudioEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class MovieDaoTest {

	@Autowired
	MovieDao movieDao;
	
	@Autowired
	StudioDao studioDao;
	
	@PersistenceContext
	EntityManager entityManager;

	@Test
	public void testShouldChangeVersionAfterSaving() throws ParseException {
		// given
		MovieEntity movie = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-10").getTime()))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withIs3D(false)
				.withLength(120)
				.build();
		// when
		movieDao.save(movie);
		entityManager.flush();
		MovieEntity toUpdate = movieDao.findAll().get(0);
		toUpdate.setGenre("Comedy");
		movie = movieDao.save(toUpdate);
		//entityManager.flush();
		// then
		assertEquals("Comedy",movieDao.findAll().get(0).getGenre());
		assertEquals(Long.valueOf(2L),movie.getVersion());
	}

	@Test
	public void shouldFindMovieByCountry() throws ParseException {
		// given
		MovieEntity movie = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-10").getTime()))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withIs3D(false)
				.withLength(120)
				.build();
		movieDao.save(movie);
		// when
		List<MovieEntity> actual = movieDao.findWithQueryDSL("Matrix");
		// then
		assertEquals("Matrix", actual.get(0).getTitle());
	}

	@Test
	public void testShouldFindByCriteriaWithAllFilled() throws ParseException {
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
				.withDateOfPremiere(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-10").getTime()))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withIs3D(false)
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
				.withMinDateOfPremiere(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-08").getTime()))
				.withMaxDateOfPremiere(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-15").getTime()))
				.withStudio(studio.getId())
				.build();

		List<MovieEntity> actual = movieDao.findByCriteria(criteria);
		// then
		assertEquals("Matrix", actual.get(0).getTitle());

	}
	
	@Test
	public void testShouldFindByCriteriaWithOnlyTypeFilledFilled() throws ParseException {
		
		MovieEntity movie = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-10").getTime()))
				.withFirstWeekRevenue(2)
				.withTotalRevenue(5)
				.withBudget(3)
				.withIs3D(false)
				.withLength(120)
				.build();
		movie = movieDao.save(movie);

		SearchCriteria criteria = SearchCriteria.newBuilder()
				.withType("Technicolor")
				.build();

		List<MovieEntity> actual = movieDao.findByCriteria(criteria);
		// then
		assertEquals("Matrix", actual.get(0).getTitle());

	}

}
