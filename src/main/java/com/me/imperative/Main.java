package com.me.imperative;

import java.util.ArrayList;
import java.util.List;
import static com.me.imperative.Main.Gender.*;

public class Main {

	public static void main(String[] args) {
		List<Person> peoples = List.of(
				
				new Person("Danny", MALE),
				new Person("Ward", MALE),
				new Person("Joy", FEMALE),
				new Person("Colleen", FEMALE),
				new Person("Davos", MALE)
				);
		
		//Imperative approach
		
		List<Person> females = new ArrayList<>();
		for(Person person : peoples) {
			if(FEMALE.equals(person.gender))
				females.add(person);
		}
		
		for(Person female : females) {
			System.out.println(female);
		}
		
		
		//Declarative approach
		
		peoples.stream()
			.filter(person ->FEMALE.equals(person.gender))
			.forEach(System.out::println);
		
	}

	static class Person {

		private String name;
		
		private Gender gender;
		
		public Person(String name, Gender gender) {
			this.name = name;
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", gender=" + gender + "]";
		}
		
	}
	
	enum Gender {
		MALE,FEMALE
	}
}
