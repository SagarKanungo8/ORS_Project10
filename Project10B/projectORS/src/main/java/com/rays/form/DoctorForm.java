package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.DoctorDTO;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class DoctorForm extends BaseForm{
	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@NotEmpty(message = "Please enter Doctor Name")
	private String doctorName;
	
	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
	@NotEmpty(message = "Please enter patient Name")
	private String patientName;

	@NotEmpty(message = "Please enter charges")
	@ValidLong(message = "Invalid input for price", allowEmpty = true)
	private String price;

	@NotEmpty(message = "Please enter  appointment date")
	@ValidDate(message = "Invalid date format or value")
	private String appointmentDate;

	private String diseaseName;

	@NotEmpty(message = "Please enter experties")
	@ValidLong(message = "Invalid input for product id", allowEmpty = true)
	@Min(value = 1, message = "experties should be greater than 0")
	private String diseaseId;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	@Override
	public BaseDTO getDto() {
		DoctorDTO dto = initDTO(new DoctorDTO());

		dto.setDoctorName(doctorName);
		dto.setPatientName(patientName);

		if (appointmentDate != null && !appointmentDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(appointmentDate);
				dto.setAppointmentDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (price != null && !price.isEmpty()) {
			dto.setPrice(Long.valueOf(price));
		}

		if (diseaseId!= null && !diseaseId.isEmpty()) {
			dto.setDiseaseId(Long.valueOf(diseaseId));
		}

		dto.setDiseaseName(diseaseName);

		return dto;
	}

}
