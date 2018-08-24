package spring.project.service;

import java.util.List;

import spring.project.model.Person;

public interface PersonService {
	public List<Person> listPersons();
	public void removePerson(int id);
	public Person getPersonById(int id);
	public void addPerson(Person person);
}
