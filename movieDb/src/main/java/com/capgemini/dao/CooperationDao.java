package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.domain.CooperationEntity;

public interface CooperationDao extends JpaRepository<CooperationEntity,Long> {
	
	@Query("select c from CooperationEntity c where c.effectiveDate between :startDate and :endDate"
			+ " or c.expirationDate between :startDate and :endDate")
	List<CooperationEntity> findByPeriod(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
	
}
