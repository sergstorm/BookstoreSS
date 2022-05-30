package CourseProject.Bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import CourseProject.Bookstore.object.Book;
import CourseProject.Bookstore.object.BookRepository;
import CourseProject.Bookstore.object.LibraryRepository;

@Controller
public class bookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired 
	private LibraryRepository lrepository;
	
	@RequestMapping(value="/*")
	public String homePage(){
		return "home";
	}
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/bookList")
	public String bookList(Model model){
		model.addAttribute("books", repository.findAll());
		return "bookList";
	}
	
	//Restful Service to get all books
	@RequestMapping(value="/books",method= RequestMethod.GET)
	public @ResponseBody List<Book> bookRest(){
		return (List<Book>) repository.findAll();
	}
	
	//Restful Service to get Student by ID
	@RequestMapping(value="/books/{id}",method=RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable ("id") Long bookID){
		return repository.findOne(bookID);
	}
	
	
	//Add a new book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String showBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("libraries", lrepository.findAll());
		return "addBook";
	}
	
	//Save a book
	@RequestMapping (value="/save", method=RequestMethod.POST)
	public String saveBook(Book book){
		repository.save(book);
		return "redirect:bookList";
	}
	
	//Edit a book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping (value="/edit/{id}")
	public String editBook(@PathVariable("id") Long id,Model model){
			model.addAttribute("book",repository.findOne(id));
			model.addAttribute("library",lrepository.findAll());
		return "editBook";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping (value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") long id, Model model){
		repository.delete(id);
		return "redirect:../bookList";
	}
	
	
}
