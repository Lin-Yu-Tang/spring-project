package com.tom.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tom.entity.Course;
import com.tom.entity.Instructor;
import com.tom.entity.InstructorDetail;
import com.tom.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {
	
	
	@Autowired
	private EntityManager em;

	@Override
	@Transactional
	public void save(Instructor instructor) {
		em.persist(instructor);
	} 
	
	public void findAll() {
		
	}

	@Override
	public Instructor findInstructorById(int theId) {
		return em.find(Instructor.class, theId);
	}

	@Override
	@Transactional
	public void deleteInstructorById(int theId) {
		Instructor find = em.find(Instructor.class, theId);
		
		setCourseWithNullInstructor(find);
		
		em.remove(find);
	}
	
	private void setCourseWithNullInstructor(Instructor instructor) {
		// 313 
		// get the courses
		List<Course> courses = instructor.getCourses();
				
		// break association of all courses for the instructor
		for (Course tmp : courses) {
			tmp.setInstructor(null);
		}
	}

	@Override
	@Transactional
	public void deleteInstructorDetailById(int theId) {
		InstructorDetail find = em.find(InstructorDetail.class, theId);
		em.remove(find);
	}
	
	
	@Override
	@Transactional
	public void deleteInstructorDetailByIdNotCascadeDelete(int theId) {
		InstructorDetail find = em.find(InstructorDetail.class, theId);
		find.getInstructor().setInstructorDetail(null);
		em.remove(find);
	}

	@Override
	public List<Course> findCoursesByInstructorId(int theId) {
		TypedQuery<Course> query = em.createQuery("from Course where instructor.id = :data ", Course.class);
		query.setParameter("data", theId);
		List<Course> courses = query.getResultList();
		
		return courses;
	}
	
	@Override
	public int testCountSQL() {
		Query query = em.createNativeQuery(" SELECT * FROM course ", Tuple.class);
		
		List<Tuple> t = query.getResultList();
		
		return roboAPItest();
//		return t.size();
	}
	
	private int roboAPItest() {
		String withSQL = "WITH TEMP AS ( SELECT * FROM course ORDER BY TITLE )";
		String withName = "TEMP";
		StringBuffer sql = new StringBuffer();
		sql.append(withSQL);
		sql.append(" SELECT COUNT(0) TOTAL FROM ").append(withName).append(" ");
		
		Query query = em.createNativeQuery(sql.toString(), Tuple.class);
		
		List<Tuple> t = query.getResultList();
		int size = Integer.parseInt(t.get(0).get("TOTAL").toString());
		System.out.println("count: " + size);
		return size;
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(int theId) {
		String sql1 = "SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id = :data ";
		
		String sql2 = "SELECT i FROM Instructor i "
				+ "JOIN FETCH i.courses "
				+ "JOIN FETCH i.instructorDetail "
				+ "WHERE i.id = :data ";
		
		// JOIN FETCH is similar to EAGER loading
		TypedQuery<Instructor> query = em.createQuery(
				sql2, Instructor.class);
		
		query.setParameter("data", theId);
		
		Instructor resultList = query.getSingleResult();
		return resultList;
	}

	@Override
	@Transactional
	public void update(Instructor instructor) {
		em.merge(instructor);
	}

	@Override
	@Transactional
	public void update(Course course) {
		em.merge(course);
	}

	@Override
	public Course findCourseById(int theId) {
		return em.find(Course.class, theId);
	}

	@Override
	@Transactional
	public void deleteCourseById(int theId) {
		Course course = em.find(Course.class, theId);
		// attempt to create delete event with null entity  " if course is null "
		em.remove(course);
	}

	@Override
	@Transactional
	public void save(Course course) {
		em.persist(course);
	}

	@Override
	public Course findCourseAndReviewByCourseId(int theId) {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c "
									+ "JOIN FETCH c.reviews "
									+ "where c.id = :data", Course.class);
		
		query.setParameter("data", theId);
		
		Course course = query.getSingleResult();
		
		return course;
	}

	@Override
	public Course findCourseAndStudentByCourseId(int theId) {
		TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c "
				+ "JOIN FETCH c.students "
				+ "where c.id = :data", Course.class);
		
		query.setParameter("data", theId);
		
		Course course = query.getSingleResult();
		return course;
	}

	@Override
	public Student findStudentAndCourseByStudentId(int theId) {
		TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s "
				+ "JOIN FETCH s.courses "
				+ "where s.id = :data", Student.class);
		
		query.setParameter("data", theId);
		
		Student student = query.getSingleResult();
		return student;
	}

	@Override
	@Transactional
	public void update(Student student) {
		em.merge(student);
	}

	@Override
	public void deleteStdentById(int theId) {
		Student find = em.find(Student.class, theId);
		
		em.remove(find);
	}
	
}
