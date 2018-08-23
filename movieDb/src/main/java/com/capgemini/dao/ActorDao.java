package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.ActorEntity;

public interface ActorDao extends CrudRepository<ActorEntity, Long>, CustomActorDao  {

}
