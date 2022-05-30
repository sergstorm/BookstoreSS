package CourseProject.Bookstore.object;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	List <Book> findBytitle(String title);

}
