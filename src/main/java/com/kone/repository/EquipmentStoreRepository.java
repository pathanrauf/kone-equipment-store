package com.kone.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kone.entity.EquipmentEntity;

@Repository
public interface EquipmentStoreRepository extends JpaRepository<EquipmentEntity, Integer>
{

	default List<EquipmentEntity> findAllByLimit(Integer limit) {
		return findAll().stream().limit(limit).collect(Collectors.toList());
	}

	@Override
	public List<EquipmentEntity> findAll();

}
