package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.dao.PersonDao;
import spring.project.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	public List<Person> listPersons() {
		return personDao.listPersons();
	}

	@Override
	public void removePerson(int id) {
		personDao.removePerson(id);
	}

	@Override
	public Person getPersonById(int id) {
		return personDao.getPersonById(id);
	}

	@Override
	public void addPerson(Person person) {
		personDao.addPerson(person);
	}

}
