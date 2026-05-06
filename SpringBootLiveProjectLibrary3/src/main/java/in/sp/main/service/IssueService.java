package in.sp.main.service;

import in.sp.main.entities.Issue;
import in.sp.main.entities.Student;

import java.util.*;
public interface IssueService {
	public boolean addDetails(Issue Is);

	public void save(Issue i1);
	public long countByStatus(String status);
	public List<Issue> GetIssue();
	public Issue getbyId(int id);
	List<Issue>findBystatus(String status);
	public List<Issue> GetIssueid(int id);
	List<Issue> findByStudentIdAndStatus(int studentId, String status);
	long countByStudentIdAndStatusIn(int studentId, List<String> status);
	long countByStudentIdAndStatus(int studentId, String status);


}
