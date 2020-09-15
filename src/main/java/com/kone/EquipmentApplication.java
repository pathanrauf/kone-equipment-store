package com.kone;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kone.entity.EquipmentEntity;
import com.kone.repository.EquipmentRepository;

@SpringBootApplication
public class EquipmentApplication implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(EquipmentApplication.class, args);
	}

	@Autowired
	private EquipmentRepository equipmentRepository;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public void run(String... args) throws Exception {
		equipmentRepository
				.save(new EquipmentEntity(1, "Address 1", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "RUNNING"));
		equipmentRepository
				.save(new EquipmentEntity(2, "Address 2", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "RUNNING"));
		equipmentRepository
				.save(new EquipmentEntity(3, "Address 3", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "STOPPED"));
		equipmentRepository
				.save(new EquipmentEntity(4, "Address 4", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "STOPPED"));

	}

}
