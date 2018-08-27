package com.capgemini.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.capgemini.dao.ActorDao;
import com.capgemini.dao.CooperationDao;
import com.capgemini.dao.MovieDao;
import com.capgemini.dao.StudioDao;
import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.mappers.ActorMapper;
import com.capgemini.service.impl.ActorServiceImpl;
import com.capgemini.types.ActorTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class ActorServiceTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ActorServiceImpl actorService;
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	StudioDao studioDao;
	
	@Autowired
	ActorDao actorDao;
	
	@Autowired
	CooperationDao cooperationDao;
	
	@Autowired
	ActorMapper actorMapper;
	
	@Test
	public void testShouldAddActorToDatabase() throws InvalidDataException{
		//when
		actorService.addActor(generateActorTO("Tom","Hardy"));
		//then
		List<ActorEntity> result = actorDao.findAll();
		assertEquals(1,result.size());
		assertEquals("Tom",result.get(0).getFirstName());
	}
	
	@Test
	public void testShouldThrowExceptionWhenActorPlaysInMoreThanMoviesInGivenPeriod() throws InvalidDataException{
		//given
		List<Long> movieIds = new ArrayList<Long>();
		movieIds.add(movieDao.save(generateSampleMovieWithTitleAndPremiere("Matrix", LocalDate.of(2018, 06, 15))).getId());
		movieIds.add(movieDao.save(generateSampleMovieWithTitleAndPremiere("Blade Runner", LocalDate.of(2018, 05, 15))).getId());
		movieIds.add(movieDao.save(generateSampleMovieWithTitleAndPremiere("Avatar", LocalDate.of(2018, 06, 19))).getId());
		movieIds.add(movieDao.save(generateSampleMovieWithTitleAndPremiere("Infiltration", LocalDate.of(2018, 04, 16))).getId());
		ActorTO toSave = ActorTO.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.withMovies(movieIds)
				.build();
		boolean thrown = false;
		//when
		try{
		actorService.addActor(toSave);
		}
		catch(InvalidDataException e){
			if(e.getMessage().equals("Too many movies in given period")){
				thrown = true;
			}
		}
		//then
		assertTrue(thrown);
	}
	
	@Test
	public void testShouldAddSuccesfullyActorWithTreeMoviesInGivenPeriod() throws InvalidDataException{
		//given
		List<Long> movieIds = new ArrayList<Long>();
		movieIds.add(movieDao.save(generateSampleMovieWithTitleAndPremiere("Matrix", LocalDate.of(2018, 06, 15))).getId());
		movieIds.add(movieDao.save(generateSampleMovieWithTitleAndPremiere("Blade Runner", LocalDate.of(2018, 05, 15))).getId());
		movieIds.add(movieDao.save(generateSampleMovieWithTitleAndPremiere("Avatar", LocalDate.of(2018, 06, 19))).getId());
		ActorTO toSave = ActorTO.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.withMovies(movieIds)
				.build();
		//when
		actorService.addActor(toSave);
		//then
		List<ActorEntity> result = actorDao.findAll();
		assertEquals(1,result.size());
		assertEquals("Tom",result.get(0).getFirstName());
	}
	
	@Test
	public void testShouldNotAddActorWithCooperation() throws InvalidDataException{
		//given
		List<Long> cooperationIds = new ArrayList<Long>();
		
		
		ActorTO toSave = ActorTO.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.withCooperations(cooperationIds)
				.build();
		boolean thrown = false;
		//when
		try{
			actorService.addActor(toSave);
			}
			catch(InvalidDataException e){
				if(e.getMessage().equals("You need to add actor first to assign cooperations")){
					thrown = true;
				}
			}
			//then
			assertTrue(thrown);
	}
	
	@Test
	public void testShouldFindActorsNotPlayingInGivenPeriod() throws InvalidDataException{
			//given
			ActorEntity tom = ActorEntity.newBuilder()
					.withFirstName("Tom")
					.withLastName("Hardy")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			ActorEntity benedict = ActorEntity.newBuilder()
					.withFirstName("Bendict")
					.withLastName("Cumberbatch")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			actorDao.save(tom);
			actorDao.save(benedict);
			//when
			List<ActorTO> result = actorService.findActorsWhoDidntActInGivenPeriod(LocalDate.of(2010,03,12), LocalDate.of(2012,03,12));
			//then
			assertEquals(2, result.size());
	}
	
	@Test
	public void testShouldNotFindActorsPlayingInGivenPeriod() throws InvalidDataException{
			//given
			ActorEntity tom = ActorEntity.newBuilder()
					.withFirstName("Tom")
					.withLastName("Hardy")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			ActorEntity benedict = ActorEntity.newBuilder()
					.withFirstName("Bendict")
					.withLastName("Cumberbatch")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			List<ActorEntity> cast = new ArrayList<ActorEntity>();
			cast.add(actorDao.save(tom));
			cast.add(actorDao.save(benedict));		
			
			MovieEntity movie = MovieEntity.newBuilder()
					.withGenre("Sci-Fi")
					.withType("Technicolor")
					.withTitle("Matrix")
					.withCountry("Polska")
					.withDateOfPremiere(LocalDate.of(2011,03,12))
					.withFirstWeekRevenue(1)
					.withTotalRevenue(5)
					.withBudget(3)
					.withThreeD(false)
					.withLength(120)
					.withCast(cast)
					.build();
			
			movieDao.save(movie);
			//when
			List<ActorTO> result = actorService.findActorsWhoDidntActInGivenPeriod(LocalDate.of(2010,03,12), LocalDate.of(2012,03,12));
			//then
			assertTrue(result.isEmpty());
	}
	
	@Test
	public void testShouldFindOneActorsPlayingInGivenPeriodAndIgnoreNotPlaying() throws InvalidDataException{
			//given
			ActorEntity tom = ActorEntity.newBuilder()
					.withFirstName("Tom")
					.withLastName("Hardy")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			ActorEntity benedict = ActorEntity.newBuilder()
					.withFirstName("Benedict")
					.withLastName("Cumberbatch")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			List<ActorEntity> cast = new ArrayList<ActorEntity>();
			cast.add(actorDao.save(tom));
			actorDao.save(benedict);		
			
			MovieEntity movie = MovieEntity.newBuilder()
					.withGenre("Sci-Fi")
					.withType("Technicolor")
					.withTitle("Matrix")
					.withCountry("Polska")
					.withDateOfPremiere(LocalDate.of(2011,03,12))
					.withFirstWeekRevenue(1)
					.withTotalRevenue(5)
					.withBudget(3)
					.withThreeD(false)
					.withLength(120)
					.withCast(cast)
					.build();
			
			movieDao.save(movie);
			//when
			List<ActorTO> result = actorService.findActorsWhoDidntActInGivenPeriod(LocalDate.of(2010,03,12), LocalDate.of(2012,03,12));
			//then
			assertEquals(1,result.size());
			assertEquals("Benedict",result.get(0).getFirstName());
	}
	
	@Test
	public void testShouldUpdateActor() throws InvalidDataException{
			//given
			ActorEntity tom = ActorEntity.newBuilder()
					.withFirstName("Tom")
					.withLastName("Hardy")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			Long actorId = actorDao.save(tom).getId();	
			
			ActorEntity modified = actorDao.findOne(actorId);
			
			modified.setFirstName("Benedict");
			//when
			actorService.updateActor(actorMapper.mapOnTO(modified));
			//then
			assertEquals(1,actorDao.findAll().size());;
			assertEquals("Benedict",actorDao.findAll().get(0).getFirstName());
	}
	
	@Test
	public void testShouldThrowExceptionWhenMissMatchVersion() throws InvalidDataException{
			//given
			ActorEntity tom = ActorEntity.newBuilder()
					.withFirstName("Tom")
					.withLastName("Hardy")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			Long actorId = actorDao.save(tom).getId();	
			
			ActorTO toUpdate = ActorTO.newBuilder()
					.withId(actorId)
					.withFirstName("Benedict")
					.withLastName("Hardy")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.withVersion(5L)
					.build();
			
			boolean thrown = false;
			//when
			try{
			actorService.updateActor(toUpdate);
			}
			catch(OptimisticLockException e){
				thrown = true;
			}
			//then
			assertTrue(thrown);
	}
	
	@Test
	public void testShouldDeleteActor() throws InvalidDataException{
			//given
			ActorEntity tom = ActorEntity.newBuilder()
					.withFirstName("Tom")
					.withLastName("Hardy")
					.withBirthDate(LocalDate.of(1983,03,12))
					.withCountry("England")
					.build();
		
			Long actorId = actorDao.save(tom).getId();	
			//when
			actorService.deleteActor(actorId);
			//then
			assertTrue(actorDao.findAll().isEmpty());
	}
	
	public MovieEntity generateSampleMovieWithTitleAndPremiere(String title, LocalDate premiere){
		return MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle(title)
				.withCountry("Polska")
				.withDateOfPremiere(premiere)
				.withFirstWeekRevenue(1)
				.withTotalRevenue(3)
				.withBudget(5)
				.withThreeD(false)
				.withLength(120)
				.build();
	}
	
	private ActorTO generateActorTO(String firstName,String lastName){
		return ActorTO.newBuilder()
				.withFirstName(firstName)
				.withLastName(lastName)
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.build();
	}
	
	
}
