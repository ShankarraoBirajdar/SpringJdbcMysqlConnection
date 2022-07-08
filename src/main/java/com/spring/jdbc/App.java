package com.spring.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.jdbc.config.AppConfig;
import com.spring.jdbc.dao.StudentDao;
import com.spring.jdbc.entities.Student;

public class App {

	public static ApplicationContext context;

	public static void main(String[] args) {
		System.out.println("Enter Below Option");
		System.out.println("1. xml Config");
		System.out.println("2. annotation Config");
		Scanner sc = new Scanner(System.in);
		int config = sc.nextInt();
		switch (config) {
		case 1:
			context = new ClassPathXmlApplicationContext("com/spring/jdbc/config/config.xml");
			break;
		case 2:
			context = new AnnotationConfigApplicationContext(com.spring.jdbc.config.AppConfig.class);
			break;
		}

		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

		input(studentDao);

	}

	static void input(StudentDao studentDao) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go = true;
		while (go) {
			System.out.println("Press 1 for Add New Student");
			System.out.println("Press 2 for Display All Students");
			System.out.println("Press 3 for Get Details of Single Student");
			System.out.println("Press 4 for Update Student Details");
			System.out.println("Press 5 for Delete Student Details");
			System.out.println("Press 6 for Exit");
			try {
				int input = Integer.parseInt(br.readLine());
				switch (input) {

				case 1:
					System.out.println("================insertStudentDetails====================");
					insertStudentDetails(br, studentDao);
					System.out.println("====================================");
					break;
				case 2:
					System.out.println("================printAllStudentDetails====================");
					printAllStudentDetails(studentDao);
					System.out.println("====================================");
					break;
				case 3:
					System.out.println("================printStudentDetails====================");
					printStudentDetails(br, studentDao);
					System.out.println("====================================");
					break;
				case 4:
					System.out.println("================updateStudentDetails====================");
					updateStudentDetails(br, studentDao);
					System.out.println("====================================");
					break;
				case 5:
					System.out.println("=================deleteStudentDetails===================");
					deleteStudentDetails(br, studentDao);
					System.out.println("====================================");
					break;
				case 6:
					go = false;
					break;

				}

			} catch (Exception e) {
				System.out.println("Invalid Input Please Try Again");
				System.out.println(e.getMessage());

			}
		}

		System.out.println("Thank You Using My Console Application");
		System.out.println("See you soon!!");

	}

	static void insertStudentDetails(BufferedReader br, StudentDao studentDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Student Id");
		int sId = Integer.parseInt(br.readLine());
		System.out.println("Enter Student Name");
		String sName = br.readLine();
		System.out.println("Enter Student City");
		String sCity = br.readLine();
		Student student1 = new Student(sId, sName, sCity);
		int i = studentDao.insert(student1);
		if (i > 0)
			System.out.println(i + " Record inserted");
		else
			System.out.println("Record Not inserted");
	}

	static void updateStudentDetails(BufferedReader br, StudentDao studentDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Student Id");
		int sId = Integer.parseInt(br.readLine());
		System.out.println("Enter Student Name");
		String sName = br.readLine();
		System.out.println("Enter Student City");
		String sCity = br.readLine();
		Student student1 = new Student(sId, sName, sCity);
		int i = studentDao.update(student1);
		if (i > 0)
			System.out.println(i + " Record Updated");
		else
			System.out.println("Record Not Updated");
	}

	static void deleteStudentDetails(BufferedReader br, StudentDao studentDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Student Id");
		int sId = Integer.parseInt(br.readLine());
		int i = studentDao.delete(sId);
		if (i > 0)
			System.out.println(i + " Record Deleted");
		else
			System.out.println("Record Not Deleted");
	}

	static void printStudentDetails(BufferedReader br, StudentDao studentDao)
			throws NumberFormatException, IOException {
		System.out.println("Enter Student Id");
		int sId = Integer.parseInt(br.readLine());
		Student student = studentDao.getStudent(sId);
		System.out.println("Id: " + student.getId());
		System.out.println("Name: " + student.getName());
		System.out.println("City: " + student.getCity());

	}

	static void printAllStudentDetails(StudentDao studentDao) throws NumberFormatException, IOException {

		List<Student> students = studentDao.getAllStudent();
		for (Student student : students) {
			System.out.println("Id: " + student.getId());
			System.out.println("Name: " + student.getName());
			System.out.println("City: " + student.getCity());
		}

	}

}
