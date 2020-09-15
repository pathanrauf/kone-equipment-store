package com.kone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kone.entity.EquipmentEntity;
import com.kone.repository.EquipmentRepository;

@Service
public class EquipmentService
{
	@Autowired
	private EquipmentRepository equipmentRepository;

	public EquipmentEntity getEquipmentById(Integer equipmentId) {
		Optional<EquipmentEntity> equipmentEntity = equipmentRepository.findById(equipmentId);

		if (equipmentEntity.isPresent()) {
			return equipmentEntity.get();
		} else {
			throw new RuntimeException("No Equipment record exist for given id");
		}
	}

	public EquipmentEntity saveEquipment(EquipmentEntity equipmentEntity) {
		Optional<EquipmentEntity> trade = equipmentRepository.findById(equipmentEntity.getEquipmentNumber());
		if (trade.isPresent()) {
			throw new RuntimeException("Already record exists");
		}
		return equipmentRepository.save(equipmentEntity);
	}

	public List<EquipmentEntity> getEquipmentByLimit(Integer limit) {
		List<EquipmentEntity> equipmentEntityList = equipmentRepository.findAllByLimit(limit);

		if (!equipmentEntityList.isEmpty()) {
			return equipmentEntityList;
		} else {
			throw new RuntimeException("No employee record exist");
		}
	}
}