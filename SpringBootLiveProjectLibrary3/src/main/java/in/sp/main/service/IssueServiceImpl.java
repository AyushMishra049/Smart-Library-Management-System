package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Issue;
import in.sp.main.repositories.IssueRepository;

@Service
public class IssueServiceImpl implements IssueService{
	
	@Autowired
	private IssueRepository issuerepository;

	/*@Override
	public List<Issue> getall() {
		return issuerepository.findAll();
	}*/

	@Override
	public boolean addDetails(Issue Is) {
		boolean status=true;
		try {
			issuerepository.save(Is);
			status=false;
		}
		catch(Exception e) {
			e.printStackTrace();
			status=false;
		}
		return status;
	}

	@Override
	public void save(Issue i1) {
		issuerepository.save(i1);
		
	}

	@Override
	public long countByStatus(String status) {
		return issuerepository.countByStatus(status);
	}

	@Override
	public List<Issue> GetIssue() {
		return issuerepository.findAll();
	}

	@Override
	public Issue getbyId(int id) {
		
		return issuerepository.getById(id);
	}

	@Override
	public List<Issue> findBystatus(String status) {
		
		return issuerepository.findBystatus(status);
	}

	@Override
	public List<Issue> GetIssueid(int id) {
		return issuerepository.findById(id);
	}



	@Override
	public List<Issue> findByStudentIdAndStatus(int studentId, String status) {
		
		return issuerepository.findByStudentIdAndStatus(studentId, status);
	}

	@Override
	public long countByStudentIdAndStatusIn(int studentId, List<String> status) {
		return issuerepository.countByStudentIdAndStatusIn(studentId, status);
	}

	@Override
	public long countByStudentIdAndStatus(int studentId, String status) {
		return issuerepository.countByStudentIdAndStatus(studentId, status);
	}




}
