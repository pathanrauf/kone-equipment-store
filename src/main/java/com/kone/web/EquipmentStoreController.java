package com.kone.web;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kone.entity.EquipmentEntity;
import com.kone.service.EquipmentStoreService;

@RestController
@RequestMapping("/equipment")
public class EquipmentStoreController
{
	@Autowired
	private EquipmentStoreService equipmentStoreService;

	@GetMapping(value = "/search")
	public List<EquipmentEntity> getEquipmentByLimit(@Positive @RequestParam(value = "limit") Integer limit) {
		return equipmentStoreService.getEquipmentByLimit(limit);
	}
	
	@GetMapping(value = "/{equipmentNumber}")
	public EquipmentEntity getEquipmentById(@Positive @PathVariable Integer equipmentNumber) {
		return equipmentStoreService.getEquipmentById(equipmentNumber);
	}

	@PostMapping
	public EquipmentEntity storeEquipment(@Valid @RequestBody EquipmentEntity equipmentEntity) {
		return equipmentStoreService.storeEquipment(equipmentEntity);
	}	
}