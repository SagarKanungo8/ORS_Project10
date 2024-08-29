package com.rays.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.DiseaseDTO;
import com.rays.dto.DoctorDTO;
@Repository
public class DoctorDAOImpl  extends BaseDAOImpl<DoctorDTO> implements DoctorDAOInt{
	@Autowired
	DiseaseDAOInt experties;
	
	@Override
	protected void populate(DoctorDTO dto, UserContext userContext) {
		if (dto.getDiseaseId() != null && dto.getDiseaseId() > 0) {
			DiseaseDTO expertiesDto = experties.findByPK(dto.getDiseaseId(), userContext);
			dto.setDiseaseName(expertiesDto.getName());
			System.out.println(dto.getDiseaseName() + "PriorityName-------");
		}

	}
	
	
	@Override
	protected List<Predicate> getWhereClause(DoctorDTO dto, CriteriaBuilder builder, Root<DoctorDTO> qRoot) {
		// Create where conditions
				List<Predicate> whereCondition = new ArrayList<>();

				if (dto.getId() != null && dto.getId() > 0) {
					whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
				}
				
				if (!isZeroNumber(dto.getPrice())) {
					whereCondition.add(builder.equal(qRoot.get("price"), dto.getPrice()));
				}

				if (isNotNull(dto.getAppointmentDate())) {
					// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
					Date searchDate = dto.getAppointmentDate();

					// Define start and end dates for the search day
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(searchDate);
					calendar.set(Calendar.HOUR_OF_DAY, 0); // Start of the day
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					Date startDate = calendar.getTime();

					calendar.set(Calendar.HOUR_OF_DAY, 23); // End of the day
					calendar.set(Calendar.MINUTE, 59);
					calendar.set(Calendar.SECOND, 59);
					Date endDate = calendar.getTime();

					// Create predicate for date range
					Predicate datePredicate = builder.between(qRoot.get("appointmentDate"), startDate, endDate);
					whereCondition.add(datePredicate);
				}

				

				if (!isEmptyString(dto.getDoctorName())) {
					whereCondition.add(builder.like(qRoot.get("doctorName"), dto.getDoctorName() + "%"));
				}
				if (!isEmptyString(dto.getPatientName())) {
					whereCondition.add(builder.like(qRoot.get("patientName"), dto.getPatientName() + "%"));
				}
				if (!isZeroNumber(dto.getDiseaseId())) {
					whereCondition.add(builder.equal(qRoot.get("diseaseId"), dto.getDiseaseId()));
				}
				if (!isEmptyString(dto.getDiseaseName())) {
					whereCondition.add(builder.like(qRoot.get("diseaseName"), dto.getDiseaseName() + "%"));
				}

				return whereCondition;
			}
	

	@Override
	public Class<DoctorDTO> getDTOClass() {
		return DoctorDTO.class;
	}

}
