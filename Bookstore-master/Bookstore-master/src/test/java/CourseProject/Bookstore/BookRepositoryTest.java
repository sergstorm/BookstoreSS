package CourseProject.Bookstore;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import CourseProject.Bookstore.object.Book;
import CourseProject.Bookstore.object.BookRepository;
import CourseProject.Bookstore.object.Library;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook(){
		List<Book> books=repository.findBytitle("Harry Potter");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("J.K.Rowling");
	}
	
	@Test
	public void createNewBook(){
		Book book=new Book(647384,"Game of Thrones","Danny",2007,34, new Library("Haaga"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
}
