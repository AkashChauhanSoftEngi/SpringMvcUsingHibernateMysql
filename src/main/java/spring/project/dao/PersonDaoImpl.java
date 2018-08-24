package spring.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.project.model.Person;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		Session session = sessionFactory.getCurrentSession();
		List<Person> personsList = session.createQuery("from Person").list();
		return personsList;
	}

	@Override
	public void removePerson(int id) {
		Session session = sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class,new Integer(id));
		if(null!=p){
			session.delete(p);
		}
	}

	@Override
	public Person getPersonById(int id) {
		Session session = sessionFactory.openSession();
		Person p = (Person)session.load(Person.class,new Integer(id));
		return p;
	}

	@Override
	public void addPerson(Person person) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(person);
	}
	
}
