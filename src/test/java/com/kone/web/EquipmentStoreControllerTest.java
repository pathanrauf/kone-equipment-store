package com.kone.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kone.AbstractTest;
import com.kone.entity.EquipmentEntity;
import com.kone.repository.EquipmentStoreRepository;

public class EquipmentStoreControllerTest extends AbstractTest
{
	@Autowired
	private EquipmentStoreRepository equipmentStoreRepository;

	@Override
	@Before
	public void setUp() throws ParseException {
		super.setUp();

		equipmentStoreRepository
				.save(new EquipmentEntity(1, "Address 1", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "RUNNING"));
		equipmentStoreRepository
				.save(new EquipmentEntity(2, "Address 2", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "RUNNING"));
		equipmentStoreRepository
				.save(new EquipmentEntity(3, "Address 3", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "STOPPED"));
		equipmentStoreRepository
				.save(new EquipmentEntity(4, "Address 4", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-11-2020").getTime()), "STOPPED"));

	}

	@Test
	public void getEquipmentStore() throws Exception {
		String uri = "/equipment/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		EquipmentEntity equipmentEntity = super.mapFromJson(content, EquipmentEntity.class);
		assertNotNull(equipmentEntity);
		assertEquals(1, equipmentEntity.getEquipmentNumber());
	}
	
	@Test
	public void getEquipmentStore_invalidEquipmentNumber() throws Exception {
		String uri = "/equipment/6";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest())
				.andReturn();

		assertEquals("No Equipment record exist for given id", mvcResult.getResolvedException().getMessage());
		assertEquals("No Equipment record exist for given id", mvcResult.getResponse().getContentAsString());
	}

	@Test
	@Ignore
	public void getEquipmentStore_withoutPathParam() throws Exception {
		String uri = "/equipment";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		EquipmentEntity equipmentEntity = super.mapFromJson(content, EquipmentEntity.class);
		assertNotNull(equipmentEntity);
		assertEquals(1, equipmentEntity.getEquipmentNumber());
	}

	@Test
	public void getAllEquipmentInLimit() throws Exception {
		String uri = "/equipment/search?limit=3";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		List<EquipmentEntity> equipmentEntityList = super.mapFromJsonList(content, EquipmentEntity.class);

		assertNotNull(equipmentEntityList);
		assertEquals(3, equipmentEntityList.size());
	}

	@Test
	public void saveEquipment() throws Exception {

		String uri = "/equipment";
		EquipmentEntity equipmentEntity = new EquipmentEntity(5, "Address 5", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-05-2020").getTime()),
				"RUNNING");
		String equipmentStoreEntityContent = super.mapToJson(equipmentEntity);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_VALUE).content(equipmentStoreEntityContent)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		equipmentEntity = super.mapFromJson(content, EquipmentEntity.class);
		assertNotNull(equipmentEntity);
		assertEquals(5, equipmentEntity.getEquipmentNumber());
	}

	@Test
	public void saveEquipment_alreadyExist() throws Exception {

		String uri = "/equipment";
		EquipmentEntity equipmentEntity = new EquipmentEntity(4, "Address 4", new Date(sdf.parse("20-05-2020").getTime()), new Date(sdf.parse("20-05-2020").getTime()),
				"RUNNING");
		String equipmentStoreEntityContent = super.mapToJson(equipmentEntity);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_VALUE).content(equipmentStoreEntityContent)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isBadRequest()).andReturn();
		assertEquals("Already record exists", mvcResult.getResolvedException().getMessage());
		assertEquals("Already record exists", mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void saveEquipment_invalidValues() throws Exception {

		String uri = "/equipment";
		EquipmentEntity equipmentEntity = new EquipmentEntity(0, "0", null, null, "");
		String equipmentStoreEntityContent = super.mapToJson(equipmentEntity);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_VALUE).content(equipmentStoreEntityContent)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest())
				.andReturn();
		assertNotNull(mvcResult.getResolvedException().getMessage());
		assertNotNull(mvcResult.getResponse().getContentAsString());
	}

}
