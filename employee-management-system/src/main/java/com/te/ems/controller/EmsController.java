package com.te.ems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.ems.dto.EmployeeDto;
import com.te.ems.dto.RegistrationDto;
import com.te.ems.response.AppResponse;
import com.te.ems.service.EmsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1")
public class EmsController {

	private final EmsService emsService;

	@GetMapping(path = "/home", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> home() {
		return ResponseEntity.ok().body("This is a home page data!");
	}

	@GetMapping(path = "/employee", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AppResponse> getEmployee() {
		EmployeeDto employeeDto = EmployeeDto.builder().empAge(22).empEmail("e@e.com").empId("TY001").build();
		return ResponseEntity.ok().body(
				AppResponse.builder().data(employeeDto).message("This is employee data").status(HttpStatus.OK).build());
	}

	@PostMapping(path = "/employee", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AppResponse> register(@RequestBody RegistrationDto registrationDto) {
		return ResponseEntity.ok().body(AppResponse.builder().data(registrationDto).message("Employee got registered")
				.status(HttpStatus.CREATED).build());
	}

	@DeleteMapping(path = "/employee/{empId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AppResponse> delete(@PathVariable(name = "empId") String empId) {
		return ResponseEntity.ok()
				.body(AppResponse.builder().data(empId).message("Employee got deleted").status(HttpStatus.OK).build());
	}
}
