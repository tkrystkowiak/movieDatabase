package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.StudioEntity;

public interface StudioDao extends CrudRepository<StudioEntity,Long>, CustomStudioDao{
}
