package in.sp.main.service;
import java.util.*;
import in.sp.main.entities.Book;

public interface BookService {
	public List<Book> getBook();
	public long totelbook();
	public Book getBookById(int id);
	public void AddBook(Book b);
	public void DeleteBook(int id);

}
