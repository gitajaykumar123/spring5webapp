package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Flow;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	/* For Dependency injection */
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Started in Bootstrap");

		Publisher publisher = new Publisher();
		
		publisher.setName("penguin");
		publisher.setAddress_Line_1("10th Cross");
		publisher.setCity("Bangalore");
		publisher.setState("Karnataka");
		publisher.setZip("560041");

		publisherRepository.save(publisher);

		System.out.println("Publisher Count: " + publisherRepository.count());

		Author eric = new Author("Eirc", "Evans");
		Book ddd = new Book("Device driver", "1233456");
		eric.getBooks().add(ddd);
		ddd.getAuthor().add(eric);

		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);

		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author Ajay = new Author("Ajay", "Kumar");
		Book ajbook = new Book("Aj book", "999999");
		Ajay.getBooks().add(ajbook);
		ajbook.getAuthor().add(Ajay);

		ajbook.setPublisher(publisher);
		publisher.getBooks().add(ajbook);

		authorRepository.save(Ajay);
		bookRepository.save(ajbook);

		System.out.println("Number of Books:" + bookRepository.count());
		System.out.println("Publisher no of books " + publisherRepository.findById(publisher.getId()).get().getBooks().size());
	}
}
