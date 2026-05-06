package in.sp.main.controllers;
import java.util.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.entities.Book;
import in.sp.main.entities.Issue;
import in.sp.main.entities.Student;
import in.sp.main.service.BookService;
import in.sp.main.service.IssueService;
import in.sp.main.service.StudentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	
	@Autowired
	private StudentService studentservice;
	
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private IssueService issueservice;
	
	@GetMapping("/")
	public String getHomePage() {
		return "index";
		
	}
	@GetMapping("/register")
	public String addStd(Model model) {
		model.addAttribute("student", new Student());
		return "Registration";
	}
	@PostMapping("/register")
	public String addStd1(@ModelAttribute("student") Student s, Model model) {
		boolean status=studentservice.addStudent(s);
		if(status) {
			model.addAttribute("successMsg", "Registered Successfully");
		}
		else {
			model.addAttribute("errorMsg", "Registeration Failed");
		}
		return "Registration";
		
		
	}
	@GetMapping("/login")
	public String getLogin() {
		return "Login";
	}
	@PostMapping("/login")
	public String Login(@ModelAttribute("student") Student s,HttpSession session, Model model) {
		Student s1=studentservice.Login(s.getEmail(), s.getPassword(), s.getRole());
		if(s1 != null) {
			
			session.setAttribute("student", s1);
			model.addAttribute("pro", s1.getName());
			model.addAttribute("adminName", s1.getName());
			
			List<Book> b1=bookservice.getBook();
			model.addAttribute("books", b1);
			List<Student>s2=studentservice.findByRole("STUDENT");
			model.addAttribute("students", s2);
			List<Issue>I2=issueservice.GetIssue();
			model.addAttribute("requests", I2);
			long b2=bookservice.totelbook();
			model.addAttribute("totalBooks", b2);
			
			long totalIssuedBooks =
			        issueservice.countByStudentIdAndStatusIn(
			                s1.getId(),
			                Arrays.asList("APPROVED", "RETURNED")
			        );

			model.addAttribute("issuedBookss", totalIssuedBooks);
			
			List<Issue> approved = issueservice.findBystatus("APPROVED");
			List<Issue> returned = issueservice.findBystatus("RETURNED");

			List<Issue> allIssued = new ArrayList<>();
			allIssued.addAll(approved);
			allIssued.addAll(returned);

			model.addAttribute("issuedBooks", allIssued);
			
			long dueBooks = issueservice.countByStudentIdAndStatus(s1.getId(), "APPROVED");

			model.addAttribute("dueBooks", dueBooks);
			
			double totalFine = 0;

			List<Issue> list =
			        issueservice.findByStudentIdAndStatus(
			                s1.getId(),
			                "APPROVED"
			        );

			for(Issue i : list) {

			    if(i.getDueDate() != null &&
			       i.getDueDate().isBefore(LocalDate.now())) {

			        long days =
			            ChronoUnit.DAYS.between(
			                i.getDueDate(),
			                LocalDate.now()
			            );

			        totalFine += days * 5; // ₹5 per day
			    }
			}

			model.addAttribute("totalFine", totalFine);

			
			List<Issue> list1 = new ArrayList<>();

			list1.addAll(issueservice.findByStudentIdAndStatus(s1.getId(), "APPROVED"));
			list1.addAll(issueservice.findByStudentIdAndStatus(s1.getId(), "RETURNED"));


			model.addAttribute("issuedList", list1);
			if(s1.getRole().equals("STUDENT")) {
				return "Studentprofile";
				
			}
			else {
				long S=issueservice.countByStatus("PENDING");
				model.addAttribute("issuedCount", S);
				long t=studentservice.countByRole("STUDENT");
				model.addAttribute("totalStudents", t);
				return "Adminprofile";
			}
			
		}
		else {
			model.addAttribute("errorMsg", "Role or Email or Password Do not matched" );
			return "Login";
		}


		
		

		
		
	}
	@PostMapping("/issue/{bookId}")
	public String issueBook(@PathVariable("bookId") int bookId, HttpSession session) {

	    Student student = (Student) session.getAttribute("student");

	    if (student == null) {
	        return "Conf"; // safety
	    }

	    Book book = bookservice.getBookById(bookId);

	    Issue issue = new Issue();
	    issue.setBookId(bookId);
	    issue.setBookname(book.getName());
	    issue.setStudentId(student.getId());
	    issue.setStudentName(student.getName());
	    issue.setIssueDate(LocalDate.now());
	    issue.setDueDate(LocalDate.now().plusDays(28));
	    issue.setStatus("PENDING");

	    issueservice.save(issue);

	    return "Conf"; // ya same page
	}

	@GetMapping("/logout")
	public String LogoutStudent() {
		return "index";
	}
	
	@PostMapping("/admin/addBook")
	public String Addbook(@ModelAttribute("books") Book b, Model model) {
		bookservice.AddBook(b);
		return "BookAddedConf";
		
	}
	@GetMapping("/admin/deleteBook/{id}")
	public String DeleteBook(@PathVariable int id) {
		bookservice.DeleteBook(id);
		return "BookDeletionConf";
		
	}
	@GetMapping("/admin/approve/{id}")
	public String IssueBook(@PathVariable int id, Issue I, Model model) {
		Issue I1=issueservice.getbyId(id);

		
		
		if(I1!=null) {
			
			I1.setStatus("APPROVED");
			issueservice.save(I1);


			
		}

		return "ApprovedConf";
		

		
	}
	
	@GetMapping("/admin/reject/{id}")
	public String IssueBook1(@PathVariable int id, Issue I) {
		Issue I1=issueservice.getbyId(id);
		
		if(I1!=null) {
			I1.setStatus("REJECTED");
			issueservice.save(I1);
			
		}
		return "RejectedBookConf";
		

		
	}
	@GetMapping("/admin/deleteStudent/{id}")
	public String DeleteStd(@PathVariable int id) {
		studentservice.DeleteStd(id);
		return "BookDeletionConf";
		
	}
	@GetMapping("/admin/return/{id}")
	public String Returedbook(@PathVariable int id ) {
		Issue I2=issueservice.getbyId(id);
		
		if(I2!=null) {
			I2.setStatus("RETURNED");
			issueservice.save(I2);
			
		}
		
		return "ReturnedConf";
	}
	
	

	

	
	
}
