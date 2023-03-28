package com.malhoura.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.malhoura.models.Student;
import com.malhoura.models.Subject;
import com.malhoura.models.Teacher;
import com.malhoura.models.Class;

public class DbRetrieve {

	private DataSource dataSource;

	public DbRetrieve(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM students";
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				String firstName = query.getString("firstname");
				String lastName = query.getString("lastname");
				int age = query.getInt("age");
				int aclass = query.getInt("class");

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);

				// add it to the list of students
				students.add(tempStudent);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return students;

	}

	public List<Teacher> getTeachers() {

		List<Teacher> teachers = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM teachers";
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				String firstName = query.getString("firstname");
				String lastName = query.getString("lastname");
				int age = query.getInt("age");

				// create new student object
				Teacher temp = new Teacher(id, firstName, lastName, age);

				// add it to the list of students
				teachers.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return teachers;

	}

	public List<Subject> getSubjects() {

		List<Subject> subjects = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM subjects";
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				String name = query.getString("name");
				String shortcut = query.getString("shortcut");

				// create new student object
				Subject temp = new Subject(id, name,shortcut);

				// add it to the list of students
				subjects.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return subjects;

	}

	public List<Class> getClasses() {

		List<Class> classes = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM classes";
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				int section = query.getInt("section");
				int subject = query.getInt("subject");
				int teacher = query.getInt("teacher");
				String time = query.getString("time");

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

				// create new student object
				Class temp = new Class(id, section, teacher_name, tempSubject.getName(), time);

				// add it to the list of students
				classes.add(temp);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return classes;

	}

	public Teacher loadTeacher(int teacherId) {

		Teacher theTeacher = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM teachers WHERE id = " + teacherId;
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				String fname = query.getString("fname");
				String lname = query.getString("lname");
				int age = query.getInt("age");
				theTeacher = new Teacher(id, fname, lname, age);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return theTeacher;

	}

	public Subject loadSubject(int subjectId) {

		Subject theSubject = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM subjects WHERE id = " + subjectId;
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				String name = query.getString("name");
				String shortcut = query.getString("shortcut");

				theSubject = new Subject(id, name,shortcut);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return theSubject;

	}

	public Class loadClass(int classId) {

		Class theClass = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM clasess WHERE id = " + classId;
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				int section = query.getInt("section");
				int subject = query.getInt("subject");
				int teacher = query.getInt("teacher");
				String time = query.getString("time");

				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);

				String teacher_name = tempTeacher.getFname() + " " + tempTeacher.getLname();

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return theClass;

	}

	public List<Student> loadClassStudents(int classId) {

		List<Student> students = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet query = null;

		try {

			// get a connection
			conn = dataSource.getConnection();

			// create sql stmt
			String sql = "SELECT * FROM students WHERE class = " + classId;
			stmt = conn.createStatement();

			// execute query
			query = stmt.executeQuery(sql);

			// process result
			while (query.next()) {

				// retrieve data from result set row
				int id = query.getInt("id");
				String firstName = query.getString("fname");
				String lastName = query.getString("lname");
				int age = query.getInt("age");
				int aclass = query.getInt("class");

				// create new student object
				Student tempStudent = new Student(id, firstName, lastName, age, aclass);
				students.add(tempStudent);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(conn, stmt, query);
		}
		return students;

	}

	private void close(Connection conn, Statement stmt, ResultSet query) {

		try {
			if (query != null) {
				query.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
