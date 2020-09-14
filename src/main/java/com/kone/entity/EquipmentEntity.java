package com.kone.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class EquipmentEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Positive
	private Integer equipmentNumber;

	@NotBlank
	private String address;

	@NotNull
	private Date contractStartDate;

	@NotNull
	private Date contractEndDate;

	@NotBlank
	private String status = "STOPPED";

	public EquipmentEntity() {

	}

	public EquipmentEntity(int equipmentNumber, String address, Date contractStartDate, Date contractEndDate, String status) {
		super();
		this.equipmentNumber = equipmentNumber;
		this.address = address;
		this.contractStartDate = contractStartDate;
		this.contractEndDate = contractEndDate;
		this.status = status;
	}

	public int getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(int equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}