package in.sp.main.repositories;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	Student findByEmail(String email);

	long countByRole(String role);
	
	List<Student>findByRole(String role);

	

}
