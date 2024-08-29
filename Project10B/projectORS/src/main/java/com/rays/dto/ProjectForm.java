package com.rays.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class ProjectForm extends BaseForm {

	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@NotEmpty(message = "Please enter Name")
	private String name;

	@NotEmpty(message = "Please enter version")
	@ValidLong(message = "Invalid input for version", allowEmpty = true)
	private String version;

	@NotEmpty(message = "Please enter open Date")
	@ValidDate(message = "Invalid date format or value")
	private String openDate;

	private String categoryName;

	@NotEmpty(message = "Please enter categoryId")
	@ValidLong(message = "Invalid input for category id", allowEmpty = true)
	@Min(value = 1, message = "categoryId should be greater than 0")
	private String categoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public BaseDTO getDto() {
		ProjectDTO dto = initDTO(new ProjectDTO());

		dto.setName(name);

		if (openDate != null && !openDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(openDate);
				dto.setOpenDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (version != null && !version.isEmpty()) {
			dto.setVersion(Long.valueOf(version));
		}

		if (categoryId != null && !categoryId.isEmpty()) {
			dto.setCategoryId(Long.valueOf(categoryId));
		}

		dto.setCategoryName(categoryName);

		return dto;
	}
}
