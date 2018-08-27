package com.capgemini.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.capgemini.dao.ActorDao;
import com.capgemini.dao.CooperationDao;
import com.capgemini.dao.MovieDao;
import com.capgemini.dao.StudioDao;
import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.MovieEntity;
import com.capgemini.domain.StudioEntity;
import com.capgemini.exceptions.InvalidDataException;
import com.capgemini.service.impl.CooperationServiceImpl;
import com.capgemini.types.CooperationTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class CooperationSeviceTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CooperationServiceImpl cooperationService;
	
	@Autowired
	ActorDao actorDao;
	
	@Autowired
	StudioDao studioDao;
	
	@Autowired
	CooperationDao cooperationDao;
	
	@Autowired
	MovieDao movieDao;
	
	@Test
	public void shouldAddCooperation() throws InvalidDataException{
		//given
		StudioEntity studio = StudioEntity.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		ActorEntity actor = ActorEntity.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.build();
		
		Long studioId = studioDao.save(studio).getId();
		Long actorId = actorDao.save(actor).getId();
		
		CooperationTO coop = CooperationTO.newBuilder()
				.withActor(actorId)
				.withStudio(studioId)
				.withEffectiveDate(LocalDate.of(1983,03,12))
				.withExpirationDate(LocalDate.of(1985,03,12))
				.build();
		//when
		cooperationService.add(coop);
		//then
		List<CooperationEntity> result = cooperationDao.findAll();
		assertEquals(1,result.size());
		assertEquals(actorId,result.get(0).getActor().getId());
		assertEquals(studioId,result.get(0).getStudio().getId());		
	}
	
	@Test
	public void shouldThrowExceptionWhenExceptionWithSmiliarPeriodExist() throws InvalidDataException{
		//given
		StudioEntity studio = StudioEntity.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		ActorEntity actor = ActorEntity.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.build();
		
		Long studioId = studioDao.save(studio).getId();
		Long actorId = actorDao.save(actor).getId();
		
		CooperationTO coop1 = CooperationTO.newBuilder()
				.withActor(actorId)
				.withStudio(studioId)
				.withEffectiveDate(LocalDate.of(1983,02,12))
				.withExpirationDate(LocalDate.of(1985,03,12))
				.build();
		cooperationService.add(coop1);
		
		CooperationTO coop2 = CooperationTO.newBuilder()
				.withActor(actorId)
				.withStudio(studioId)
				.withEffectiveDate(LocalDate.of(1983,02,22))
				.withExpirationDate(LocalDate.of(1985,04,12))
				.build();
		boolean thrown = false;
		//when
		try{
		cooperationService.add(coop2);
		}
		catch(InvalidDataException e){
			if(e.getMessage().equals("Actor can have only one cooperation in given period of time")){
				thrown = true;
			}
		}
		//then
		assertTrue(thrown);
	}
	
	@Test
	public void shouldThrowExceptionWhenActorPlaysInOtherStudioMovieDuringCooperation() throws InvalidDataException{
		//given
		StudioEntity studioSuper = StudioEntity.newBuilder()
				.withName("Super")
				.withCountry("Polska")
				.build();
		
		StudioEntity studioExtra = StudioEntity.newBuilder()
				.withName("Extra")
				.withCountry("Polska")
				.build();
		
		ActorEntity actor = ActorEntity.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.build();
		
		List<ActorEntity> cast = new ArrayList<ActorEntity>();
		cast.add(actor);
		
		MovieEntity movie = MovieEntity.newBuilder()
				.withGenre("Sci-Fi")
				.withType("Technicolor")
				.withTitle("Matrix")
				.withCountry("Polska")
				.withDateOfPremiere(LocalDate.of(1984,03,12))
				.withFirstWeekRevenue(1)
				.withTotalRevenue(5)
				.withBudget(3)
				.withThreeD(false)
				.withLength(120)
				.withStudio(studioExtra)
				.withCast(cast)
				.build();
		
		
		
		Long studioId = studioDao.save(studioSuper).getId();
		Long studio2Id = studioDao.save(studioExtra).getId();
		Long actorId = actorDao.save(actor).getId();
		Long movieId = movieDao.save(movie).getId();
		
		CooperationTO coop = CooperationTO.newBuilder()
				.withActor(actorId)
				.withStudio(studioId)
				.withEffectiveDate(LocalDate.of(1983,02,12))
				.withExpirationDate(LocalDate.of(1985,03,12))
				.build();
		
		
		boolean thrown = false;
		//when
		try{
		cooperationService.add(coop);
		}
		catch(InvalidDataException e){
			if(e.getMessage().equals("During the cooperation actor can play only for given studio")){
				thrown = true;
			}
		}
		//then
		assertTrue(thrown);
	}
	
}
