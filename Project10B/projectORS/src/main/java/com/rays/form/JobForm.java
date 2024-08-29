package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.JobDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class JobForm extends BaseForm {

	@Pattern(regexp = "^([A-Z][a-z]+(\\s[A-Z][a-z]+)*){1,100}$", message = "Invalid title")
	@NotEmpty(message = "Please enter title")
	private String title;

	@NotEmpty(message = "Please enter experience")
	@ValidLong(message = "Invalid input for experience", allowEmpty = true)
	private String experience;

	@NotEmpty(message = "Please enter date Of Opening")
	@ValidDate(message = "Invalid date format or value")
	private String dateOfOpening;

	private String statusName;

	@NotEmpty(message = "Please enter statusId")
	@ValidLong(message = "Invalid input for status id", allowEmpty = true)
	@Min(value = 1, message = "statusId should be greater than 0")
	private String statusId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDateOfOpening() {
		return dateOfOpening;
	}

	public void setDateOfOpening(String dateOfOpening) {
		this.dateOfOpening = dateOfOpening;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	@Override
	public BaseDTO getDto() {
		JobDTO dto = initDTO(new JobDTO());

		dto.setTitle(title);

		if (dateOfOpening != null && !dateOfOpening.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(dateOfOpening);
				dto.setDateOfOpening(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (experience != null && !experience.isEmpty()) {
			dto.setExperience(Long.valueOf(experience));
		}

		if (statusId != null && !statusId.isEmpty()) {
			dto.setStatusId(Long.valueOf(statusId));
		}

		dto.setStatusName(statusName);

		return dto;
	}

}
