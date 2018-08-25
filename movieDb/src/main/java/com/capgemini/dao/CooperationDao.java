package com.capgemini.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.domain.CooperationEntity;

public interface CooperationDao extends JpaRepository<CooperationEntity,Long> {
	
	@Query("select c from CooperationEntity m where m.effectiveDate between :startDate and :endDate"
			+ " or m.expirationDate between :startDate and :endDate")
	List<CooperationEntity> findByPeriod(LocalDate date, LocalDate date2);
	
}
