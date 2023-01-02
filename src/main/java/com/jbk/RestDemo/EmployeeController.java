package com.jbk.RestDemo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import lombok.val;
import lombok.experimental.UtilityClass;
import net.bytebuddy.implementation.bind.annotation.Super;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepo emplRepo ;

	@GetMapping("/getmsg/{userName}")
	public String getMes(@PathVariable String userName) {

		return "Hello GoodMorning " + userName;

	}

	@GetMapping("/getMesBaseOnTIme/{userName}")
	public String grettings(@PathVariable String userName) {

		LocalTime timeNow = LocalTime.now();
		LocalTime time1 = LocalTime.parse("12:00:00");
		LocalTime time2 = LocalTime.parse("17:00:00");
		LocalTime time3 = LocalTime.parse("21:00:00");
		LocalTime time4 = LocalTime.parse("04:00:00");

		if (timeNow.isBefore(time1)) {

			return "Good Morning" + " " + userName;

		} else if (timeNow.isAfter(time1) && timeNow.isBefore(time2)) {

			return "good Afternoon" + " " + userName;

		} else if (timeNow.isAfter(time2) && timeNow.isBefore(time3)) {
			
			return "good Evining" + " " + userName;
		
		} else if (timeNow.isAfter(time3)) {
			
			return "Good Night" + " " + userName ;
		}else {
			
			return "So Ja Beta Tera Kuch Kam Nahi he"  + " " + userName ;
		}

		// boolean before = timeTo.isBefore(timeNow); // boolean for `before`
		// boolean after = timeTo.isAfter(timeNow); // boolean for `after`

	}
	
	@PostMapping("/createemployee")
	public Employee createEmployee (@RequestBody Employee emp) {

		Set<PhoneNo> phonenoset = emp.getPhoneNo();

		phonenoset.forEach(phoneNo -> phoneNo.setEmployee(emp));
		
		return emplRepo.save(emp);
	}

	@GetMapping(value = "/findallemployee")
	public List<Employee> finedAllEmployee (){

            return  emplRepo.findAll();
	}

	@GetMapping("/findgretersalary")
	public List<Employee> findGreterSalary () {
		List<Employee> list1=emplRepo.findAll();
	    Stream<Employee> list2	=list1.stream().filter(employee -> employee.getEmplSalary() >= 50000);
	    List<Employee> list3=list2.collect(Collectors.toList());
		return  list3;
	}

	@GetMapping ("/findemployeebyid/{emplId}")
	public String findEmployeeById (@PathVariable int emplId){
		Optional<Employee> a1= emplRepo.findById(emplId);
		if (a1.isPresent()){

			return a1.toString();
		}else {
			return  "Id Was Incorrect,Pls Check and Try Again";
		}
	}
	@PutMapping("/updateemployee/{emplId}")
	public String updateEmployeeByID (@RequestBody Employee employee , @PathVariable int emplId){
		Optional<Employee> employee1 = emplRepo.findById(emplId);
		if (employee1.isPresent()){

			employee1.get().setEmplName(employee.getEmplName());
			employee1.get().setEmplSalary(employee.getEmplSalary());
			return emplRepo.save(employee1.get()).toString();

		}else {
			return "Not Found This Id " + emplId;
		}
	}


	
	@DeleteMapping("/deleteemployeebyid/{emplId}")
	public String deleteEmployeeById (@PathVariable int emplId) {
		Optional<Employee> employee1 = emplRepo.findById(emplId);
		if ( employee1.isPresent()){
			emplRepo.deleteById(emplId);
			return "Employee With Id = "+emplId+" Deleted Successfully";
		}else {
			return "Employee With Id = "+emplId+" Not Found";
		}
	}



}
