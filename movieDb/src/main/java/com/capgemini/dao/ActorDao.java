package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.ActorEntity;

public interface ActorDao extends JpaRepository<ActorEntity, Long>, CustomActorDao  {

}
