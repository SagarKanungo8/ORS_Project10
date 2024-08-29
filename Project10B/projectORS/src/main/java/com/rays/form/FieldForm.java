package com.rays.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.FieldDTO;
import com.rays.validation.ValidLong;

public class FieldForm extends BaseForm {

	@NotEmpty(message = "Please enter Labell")
	private String labell;
	
	@NotEmpty(message = "Please enter description")
	private String description;
	
	@NotEmpty(message = "Please enter active")
	private String active;

	private String typeName;

	@NotEmpty(message = "Please enter typeId")
	@ValidLong(message = "Invalid input for type id", allowEmpty = true)
	@Min(value = 1, message = "typeId should be greater than 0")
	private String typeId;

	public String getLabell() {
		return labell;
	}

	public void setLabell(String labell) {
		this.labell = labell;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	
	@Override
	public BaseDTO getDto() {
		FieldDTO dto = initDTO(new FieldDTO());

		dto.setLabell(labell);
		
		dto.setDescription(description);
        
		dto.setActive(active);

		if (typeId != null && !typeId.isEmpty()) {
			dto.setTypeId(Long.valueOf(typeId));
		}

		dto.setTypeName(typeName);

		return dto;
	}
}
