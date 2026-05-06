package in.sp.main.service;
import java.util.*;
import in.sp.main.entities.Student;

public interface StudentService {
	public boolean addStudent(Student std);
	public Student Login(String email, String password, String role);
	public Student getStudentById(int studentId);
	long countByRole(String role);
	public List<Student> GetAllStd();
	public void DeleteStd(int id);
	List<Student>findByRole(String role);


}
