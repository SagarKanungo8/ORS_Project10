package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.CategoryDTO;
import com.rays.form.CategoryForm;
import com.rays.service.CategoryServiceInt;

@RestController
@RequestMapping(value = "Category")
public class CategoryCtl extends BaseCtl<CategoryForm, CategoryDTO, CategoryServiceInt> {

	@Autowired
	private CategoryServiceInt categoryService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
		CategoryDTO dto = new CategoryDTO();
		List<DropdownList> list = categoryService.search(dto, userContext);
		res.addResult("categoryList", list);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		CategoryDTO dto = baseService.findByName(name, userContext);
		System.out.println("Product " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}