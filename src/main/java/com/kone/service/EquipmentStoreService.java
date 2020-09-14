package com.kone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kone.entity.EquipmentEntity;
import com.kone.repository.EquipmentStoreRepository;

@Service
public class EquipmentStoreService
{
	@Autowired
	private EquipmentStoreRepository equipmentStoreRepository;

	public EquipmentEntity getEquipmentById(Integer equipmentId) {
		Optional<EquipmentEntity> equipmentEntity = equipmentStoreRepository.findById(equipmentId);

		if (equipmentEntity.isPresent()) {
			return equipmentEntity.get();
		} else {
			throw new RuntimeException("No Equipment record exist for given id");
		}
	}

	public EquipmentEntity storeEquipment(EquipmentEntity equipmentEntity) {
		Optional<EquipmentEntity> trade = equipmentStoreRepository.findById(equipmentEntity.getEquipmentNumber());
		if (trade.isPresent()) {
			throw new RuntimeException("Already record exists");
		}
		return equipmentStoreRepository.save(equipmentEntity);
	}

	public List<EquipmentEntity> getEquipmentByLimit(Integer limit) {
		List<EquipmentEntity> equipmentEntityList = equipmentStoreRepository.findAllByLimit(limit);

		if (!equipmentEntityList.isEmpty()) {
			return equipmentEntityList;
		} else {
			throw new RuntimeException("No employee record exist");
		}
	}
}