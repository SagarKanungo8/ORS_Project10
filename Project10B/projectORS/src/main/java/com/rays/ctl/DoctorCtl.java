package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.DiseaseDTO;
import com.rays.dto.DoctorDTO;
import com.rays.form.DoctorForm;
import com.rays.service.DiseaseServiceInt;
import com.rays.service.DoctorServiceInt;

@RestController
@RequestMapping(value = "Doctor")
public class DoctorCtl extends BaseCtl<DoctorForm, DoctorDTO, DoctorServiceInt>{
	@Autowired
	DiseaseServiceInt expertiesService;

	@Autowired
	DoctorServiceInt doctorService;
	
	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Rahul");
		ORSResponse res = new ORSResponse(true);
		DiseaseDTO dto = new DiseaseDTO();
		List<DropdownList> list = expertiesService.search(dto, userContext);
		res.addResult("expertiesList", list);
		return res;
	}

}
