package in.sp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import in.sp.main.entities.Book;
import in.sp.main.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookservice;
	


}
