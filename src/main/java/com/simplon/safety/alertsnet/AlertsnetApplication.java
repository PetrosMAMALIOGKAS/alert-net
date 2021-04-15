package com.simplon.safety.alertsnet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.simplon.safety.alertsnet.model.Person;

@SpringBootApplication
public class AlertsnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertsnetApplication.class, args);
	}
	
	public static List<Person> personsData = new ArrayList<Person>();

}
