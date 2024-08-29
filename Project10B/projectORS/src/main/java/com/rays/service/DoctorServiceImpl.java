package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.DoctorDAOInt;
import com.rays.dto.DoctorDTO;

@Service
@Transactional
public class DoctorServiceImpl extends BaseServiceImpl<DoctorDTO, DoctorDAOInt> implements DoctorServiceInt{

	@Autowired
	DoctorDAOInt doctorDAO;
}
