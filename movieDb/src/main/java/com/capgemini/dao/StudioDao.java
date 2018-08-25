package com.capgemini.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.domain.StudioEntity;

public interface StudioDao extends JpaRepository<StudioEntity,Long>, CustomStudioDao{
}
