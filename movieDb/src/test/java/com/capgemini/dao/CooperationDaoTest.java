package com.capgemini.dao;

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

import com.capgemini.domain.ActorEntity;
import com.capgemini.domain.CooperationEntity;
import com.capgemini.domain.StudioEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("hsql")
public class CooperationDaoTest {
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CooperationDao cooperationDao;
	
	@Autowired
	StudioDao studioDao;
	
	@Autowired
	ActorDao actorDao;
	
	@Test
	public void testShouldNotDeleteActorAndStudioAfterDeletingCooperation(){
		//given
		StudioEntity studio = StudioEntity.newBuilder()
				.withName("Extra")
				.withCountry("Polska")
				.build();
		
		ActorEntity actor = ActorEntity.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.build();
		
		CooperationEntity coop = CooperationEntity.newBuilder()
				.withActor(actor)
				.withStudio(studio)
				.withEffectiveDate(LocalDate.of(2012,03,12))
				.withExpirationDate(LocalDate.of(2015,03,12))
				.build();
		
		studioDao.save(studio);
		actorDao.save(actor);
		Long coopId = cooperationDao.save(coop).getId();
		//when
		cooperationDao.delete(coopId);
		//then
		assertTrue(cooperationDao.findAll().isEmpty());
		assertEquals(1,studioDao.findAll().size());
		assertEquals(1,actorDao.findAll().size());

	}
	
	@Test
	public void testShouldDeleteCooperationWhenDeletingActorButNotStudio(){
		//given
		StudioEntity studio = StudioEntity.newBuilder()
				.withName("Extra")
				.withCountry("Polska")
				.build();
		
		ActorEntity actor = ActorEntity.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.build();
		
		CooperationEntity coop = CooperationEntity.newBuilder()
				.withActor(actor)
				.withStudio(studio)
				.withEffectiveDate(LocalDate.of(2012,03,12))
				.withExpirationDate(LocalDate.of(2015,03,12))
				.build();
		List<CooperationEntity> coops = new ArrayList<CooperationEntity>();
		coops.add(coop);
		
		studioDao.save(studio);
		Long actorId = actorDao.save(actor).getId();
		cooperationDao.save(coop);
		
		ActorEntity modified = actorDao.findOne(actorId);
		modified.setCooperations(coops);
		actorDao.save(modified);

		//when
		actorDao.delete(actorId);
		//then
		assertTrue(cooperationDao.findAll().isEmpty());
		assertEquals(1,studioDao.findAll().size());
		assertTrue(actorDao.findAll().isEmpty());

	}
	
	@Test
	public void testShouldDeleteCooperationWhenDeletingStudioButNotActor(){
		//given
		StudioEntity studio = StudioEntity.newBuilder()
				.withName("Extra")
				.withCountry("Polska")
				.build();
		
		ActorEntity actor = ActorEntity.newBuilder()
				.withFirstName("Tom")
				.withLastName("Hardy")
				.withBirthDate(LocalDate.of(1983,03,12))
				.withCountry("England")
				.build();
		
		CooperationEntity coop = CooperationEntity.newBuilder()
				.withActor(actor)
				.withStudio(studio)
				.withEffectiveDate(LocalDate.of(2012,03,12))
				.withExpirationDate(LocalDate.of(2015,03,12))
				.build();
		List<CooperationEntity> coops = new ArrayList<CooperationEntity>();
		coops.add(coop);
		
		Long studioId =studioDao.save(studio).getId();
		actorDao.save(actor);
		cooperationDao.save(coop);
		
		StudioEntity modified = studioDao.findOne(studioId);
		modified.setCooperation(coops);
		studioDao.save(modified);

		//when
		studioDao.delete(studioId);
		//then
		assertTrue(cooperationDao.findAll().isEmpty());
		assertTrue(studioDao.findAll().isEmpty());
		assertEquals(1,actorDao.findAll().size());

	}
	
}