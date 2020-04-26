package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	private static Map<String,Person> personRepo= new HashMap();
	
	static {
		Person pr = new Person();
		pr.setId("RA2345");
		pr.setName("Ramesh");
				personRepo.put(pr.getId(), pr);	
				
				Person pr1 = new Person();
				pr.setId("RA2346");
				pr.setName("Gopal Ganesh");
						personRepo.put(pr.getId(), pr);			
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
		@RequestMapping("/")
	public String hello() {
		return "Sahibabad Gaziabad Govind Ji, Chandra Upreti";
	}
	
	@RequestMapping(value="/persons",method= RequestMethod.GET)
	public ResponseEntity<Object> getPerson() 
	{   
		return new ResponseEntity<>(personRepo.values(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/person", method=RequestMethod.POST) 
	 public ResponseEntity<Object> createProduct(@RequestBody Person person) { 
		personRepo.put(person.getId(), person); 
	  return new ResponseEntity<>("Person is created successfully", HttpStatus.CREATED); 
	 }
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.PUT) 
	 public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Person person) {  
		personRepo.remove(id); 
		person.setId(id); 
		personRepo.put(id, person); 
	  return new ResponseEntity<>("Person is updated successsfully", HttpStatus.OK); 
	 } 
	
	
	@RequestMapping(value="/person/{id}", method=RequestMethod.DELETE) 
	 public ResponseEntity<Object> delete(@PathVariable("id") String id) {  
		personRepo.remove(id); 
	  return new ResponseEntity<>("Person is deleted successsfully", HttpStatus.OK); 
	 }


}

class Person{
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String id;
	
}

