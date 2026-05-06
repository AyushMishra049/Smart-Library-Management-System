package in.sp.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Issue;
import in.sp.main.entities.Student;

public interface IssueRepository extends JpaRepository<Issue, Integer>{
	public long countByStatus(String status);
	List<Issue>findBystatus(String status);
	List<Issue>findById(int id);
	List<Issue> findByStudentIdAndStatus(int studentId, String status);
	long countByStudentIdAndStatusIn(int studentId, List<String> status);
	long countByStudentIdAndStatus(int studentId, String status);



	
	

}
