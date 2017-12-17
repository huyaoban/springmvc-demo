package com.huyaoban.springmvc.validator;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huyaoban.springmvc.model.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
		ValidationUtils.rejectIfEmpty(errors, "price", "price.required");
		ValidationUtils.rejectIfEmpty(errors, "productionDate",
				"productiondate.required");

		BigDecimal price = product.getPrice();
		if (price != null && price.compareTo(BigDecimal.ZERO) < 0) {
			errors.rejectValue("price", "price.negative");
		}

		Date productionDate = product.getProductionDate();
		if (productionDate != null) {
			if (productionDate.after(new Date())) {
				errors.rejectValue("productionDate", "productiondate.invalid");
			}
		}
	}

}
