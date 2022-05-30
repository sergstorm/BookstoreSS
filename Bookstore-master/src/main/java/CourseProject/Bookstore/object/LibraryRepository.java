package CourseProject.Bookstore.object;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Long>{
	List<Library> findByLibName(String name);
}
