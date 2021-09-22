package com.dev.DemoHib;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App {

	private static Session session;

	private static void insertNewAlien(int id, Name name, String color) {
		Alien alien = new Alien();
		alien.setId(id);
		alien.setName(name);
		alien.setColor(color);

		Transaction transaction = session.beginTransaction();
		session.save(alien);
		transaction.commit();
	}

	private static void getAlienById(int i) {
		Transaction transaction = session.beginTransaction();
		Alien result = (Alien) session.get(Alien.class, 101);
		transaction.commit();
		System.out.println(result.toString());
	}

	static {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class).addAnnotatedClass(Tool.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
	}

	public static void main(String[] args) {
		Name name01 = new Name();
		name01.setFirst("f01");
		name01.setMiddle("m01");
		name01.setLast("l01");
		insertNewAlien(101, name01, "Green");
		getAlienById(101);

		Name name02 = new Name();
		name02.setFirst("f02");
		name02.setMiddle("m02");
		name02.setLast("l02");
		insertNewAlien(102, name02, "Blue");
		getAlienById(102);
		
		Laptop laptop = new Laptop();
		laptop.setId(101);
		laptop.setName("Dell");
		insertLaptop(laptop);
		
		List<Laptop> laptops = new ArrayList<Laptop>();
		laptops.add(laptop);
		
		Tool tool = new Tool();
		tool.setId(101);
		tool.setName("t01");
		insertTool(tool);
		
		List<Tool> tools = new ArrayList<Tool>();
		tools.add(tool);
		
		Student student01 = new Student();
		student01.setName("s01");
		student01.setRollno(1);
		student01.setMarks(50);
		student01.setLaptops(laptops);
		student01.setTools(tools);
		insertStudent(student01);

		Student student02 = new Student();
		student02.setName("s02");
		student02.setRollno(2);
		student02.setMarks(90);
		student02.setTools(tools);
		insertStudent(student02);
		
		
	}

	private static void insertTool(Tool tool) {
		session.beginTransaction();
		session.save(tool);
		session.getTransaction().commit();
		
	}

	private static void insertStudent(Student student) {
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();
	}

	private static void insertLaptop(Laptop laptop) {
		session.beginTransaction();
		session.save(laptop);
		session.getTransaction().commit();
	}

}
