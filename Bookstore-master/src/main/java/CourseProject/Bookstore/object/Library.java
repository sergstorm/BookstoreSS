package CourseProject.Bookstore.object;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Library {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long libID;
	private String libName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "library")
	private List<Book> books;
	public Library(){}
	public Library(String libName) {
		super();
		this.libName = libName;
	}
	public Long getLibID() {
		return libID;
	}
	public void setLibID(Long libID) {
		this.libID = libID;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Library [libID=" + libID + ", libName=" + libName + ", books=" + books + "]";
	}
	
	

}
