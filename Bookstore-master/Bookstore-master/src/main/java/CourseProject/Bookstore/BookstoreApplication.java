package CourseProject.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import CourseProject.Bookstore.object.Book;
import CourseProject.Bookstore.object.BookRepository;
import CourseProject.Bookstore.object.Library;
import CourseProject.Bookstore.object.LibraryRepository;
import CourseProject.Bookstore.object.User;
import CourseProject.Bookstore.object.UserRepository;



@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, LibraryRepository lrepository, UserRepository urepository){
		return (args) -> {
			log.info("Save a couple of libraries");
			lrepository.save(new Library("Pasila"));
			lrepository.save(new Library("Haaga"));
			lrepository.save(new Library("Malmi"));
			
			
/*		repository.save(new Book(173323,"Harry Potter","J.K.Rowling", 1998, 20, lrepository.findByLibName("Pasila").get(0)));
		repository.save(new Book(635352,"Alchemist","Paulo Coelho", 1993, 35, lrepository.findByLibName("Pasila").get(0)));*/
		
		// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$eL6DBdC894FJsNOzvk6vsuMGRUn.VFoSpBH2k4HLv4sghiBtZivAy", "USER");
			User user2 = new User("admin", "$2a$06$8fdX7nZReXdIk5MBGMipCuTasV5JcWc92zcJ1blt6SjmK9xL8pzaG", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("Fetch some books");
			for(Book book: repository.findAll()){
				log.info(book.toString());
			}
		};
	}
	
}
