package spring.project.dao;

import java.util.List;

import spring.project.model.Person;

public interface PersonDao {
	public List<Person> listPersons();
	public void removePerson(int id);
	public Person getPersonById(int id);
	public void addPerson(Person person);
}
