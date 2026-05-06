package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Student;
import in.sp.main.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentrepository;
	
	@Override
	public boolean addStudent(Student std) {
		boolean status=false;
		
		try {
			studentrepository.save(std);
			status=true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			status=false;
		}
		return status;
		
	}

	@Override
	public Student Login(String email, String password, String role) {
		Student s=studentrepository.findByEmail(email);
		if(s != null && s.getPassword().equals(password) && s.getRole().equals(role)) {
			return s;
		}

		return null;
	}

	@Override
	public Student getStudentById(int studentId) {
		return studentrepository.getById(studentId);
	}

	@Override
	public long countByRole(String role) {
		return studentrepository.countByRole(role);
	}

	@Override
	public List<Student> GetAllStd() {
		return studentrepository.findAll();
	}

	@Override
	public void DeleteStd(int id) {
		studentrepository.deleteById(id);
		
	}

	@Override
	public List<Student> findByRole(String role) {
		return studentrepository.findByRole(role);
	}


	
	

}
