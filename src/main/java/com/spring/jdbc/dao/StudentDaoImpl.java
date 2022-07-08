package com.spring.jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.spring.jdbc.entities.Student;

@Component("studentDao")
public class StudentDaoImpl implements StudentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insert(Student student) {
		// insert Query
		String query = "insert into Student(id,name,city) values(?,?,?)";
		int i = jdbcTemplate.update(query, student.getId(), student.getName(), student.getCity());
		return i;
	}

	// select row by id
	public Student getStudent(int id) {
		String query = "select * from Student where id=?";
		RowMapper<Student> rowMapper = new RowMapperImpl();
		Student student = this.jdbcTemplate.queryForObject(query, rowMapper, id);
		
//		If you use Anonymous class then you don't need to implements RowMapper interface 
//		Student student = this.jdbcTemplate.queryForObject(query, new RowMapper<Student>(){
//			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Student s =new Student();
//				s.setId(rs.getInt(1));
//				s.setName(rs.getString(2));
//				s.setCity(rs.getString(3));
//				return s;
//			}
//		}, id);
		return student;
	}

	// Select Multiple Rows
	public List<Student> getAllStudent() {
		String query = "select * from Student";
		List<Student> Students = this.jdbcTemplate.query(query, new RowMapperImpl());
		return Students;
	}

	public int update(Student student) {
		// update Query
		String query = "update Student set name=?, city=? where id=?";
		int i = jdbcTemplate.update(query, student.getName(), student.getCity(), student.getId());
		return i;
	}

	public int delete(int id) {
		// update Query
		String query = "delete from student where id=?";
		int i = jdbcTemplate.update(query, id);
		return i;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
