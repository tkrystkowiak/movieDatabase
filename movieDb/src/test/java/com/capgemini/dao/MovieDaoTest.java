package com.capgemini.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.StudioEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class MovieDaoTest {
	
	@Autowired
	MovieDao movieDao;
	
	@Test
	public void shouldFindMovieByCountry() throws ParseException{
		//given
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
		//when
		List<MovieEntity> actual = movieDao.findByTitle("Matrix");
		//then
		assertEquals("Matrix",actual.get(0).getTitle());
	}
	
}
