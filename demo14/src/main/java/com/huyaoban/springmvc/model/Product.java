package com.huyaoban.springmvc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(min = 1, max = 10)
	private String name;
	private String description;
	private BigDecimal price;
	@Past
	private Date productionDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

}
