import { Component, OnInit } from '@angular/core';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent extends BaseCtl {

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute) {
    super(locator.endpoints.DOCTOR, locator, route);
  }
  

  validateForm(form) {
    let flag = true;
    let validator = this.serviceLocator.dataValidator;
    flag = flag && validator.isNotNullObject(form.userId);
    flag = flag && validator.isNotNullObject(form.transactiontypeId);
    flag = flag && validator.isNotNullObject(form.startDate);
    flag = flag && validator.isNotNullObject(form.endDate);

    return flag;
  }

  

  populateForm(form, data) {
    form.id = data.id;
    form.doctorName = data.doctorName;
    form.patientName = data.patientName;
    form.appointmentDate=data.appointmentDate;
    form.price=data.price;
    form.diseaseId=data.diseaseId;

   // form.transactiontypeId = data.transactiontypeId;
   // form.startDate= this.parseDate(data.startDate); // Assuming data.date is a string date
   // form.endDate = data.endDate;
  }

  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
  }

  validateAlphabetInput(event) {
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);

    // Regular expression to test if the character is a letter
    const letterRegex = /^[a-zA-Z\s]+$/;

    // Test if the input matches the allowed characters
    if (!letterRegex.test(charStr)) {
      event.preventDefault();
    }

    // Optionally, check the entire value against the name format regex
    const inputElement = event.target;
    const inputValue = inputElement.value + charStr; // Add the current character to the input value

    // Regex for valid name format "FirstName LastName"
    const nameRegex = /^[A-Z][a-z]+ [A-Z][a-z]+$/;

    // Check if the current value matches the valid name format
    if (!nameRegex.test(inputValue)) {
      // Handle invalid input (e.g., disable submit button, show error message)
      // Example:
      inputElement.classList.add('invalid'); // Apply CSS class for invalid input
    } else {
      inputElement.classList.remove('invalid'); // Remove invalid CSS class if format is valid
    }
  }
  validateQuantity(event: KeyboardEvent) {
    const input = event.target as HTMLInputElement;
    const pattern = /^[0-9]*$/;
    const key = event.key;

    // Allow numbers only
    if (!pattern.test(key)) {
      event.preventDefault();
    }

    // Ensure the value is between 1 and 1000
    const currentValue = input.value + key;
    if (parseInt(currentValue) > 1000) {
      event.preventDefault();
    }
  }
  onUpload(userform: FormData) {
    this.submit();
    console.log(this.form.data.id + '---- after submit');

  }
  submit() {
    var _self = this;
    console.log('in submit');
    console.log(this.form);
    console.log(this.form.data);
  
    this.serviceLocator.httpService.post(this.api.save, this.form.data, function (res) {
      _self.form.message = '';
      _self.form.inputerror = {}; // Clear input errors here
  
      if (res.success) {
        _self.form.error = false; // Set error to false on success
        _self.form.message = "Data is saved";
        _self.form.data.id = res.result.data;
        console.log(_self.form.data.id);
        console.log("----------Rahul----------.");
  
        // Clear form data if needed
        // _self.form.data = {};
  
      } else {
        _self.form.error = true;
        if (res.result.inputerror) {
          _self.form.inputerror = res.result.inputerror;
        }
        _self.form.message = res.result.message;
      }
      console.log('FORM', _self.form);
    });
  }

  validateNumber(event: KeyboardEvent): void {
    const inputChar = String.fromCharCode(event.charCode);
  
    // Get the current value of the input field
    const inputElement = event.target as HTMLInputElement;
    const currentValue = inputElement.value;
  
    // If the first character is being entered
    if (currentValue.length === 0) {
      if (!/^[6-9]$/.test(inputChar)) {
        // Invalid first character (not greater than 5), prevent input
        event.preventDefault();
      }
    } else {
      // For subsequent characters, ensure they are digits
      if (!/^\d+$/.test(inputChar)) {
        // Invalid character, prevent input
        event.preventDefault();
      }
    }
  }
  




 
}
