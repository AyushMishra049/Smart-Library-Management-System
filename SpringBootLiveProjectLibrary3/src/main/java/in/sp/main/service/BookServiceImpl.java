package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Book;
import in.sp.main.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookrepository;

	@Override
	public List<Book> getBook() {
		return bookrepository.findAll();
	}

	@Override
	public long totelbook() {
		return bookrepository.count();
	}

	@Override
	public Book getBookById(int id) {
		return bookrepository.getById(id);
		
	}

	@Override
	public void AddBook(Book b) {
		bookrepository.save(b);
		
	}

	@Override
	public void DeleteBook(int id) {
		bookrepository.deleteById(id);;
		
	}
	
	




	

}
