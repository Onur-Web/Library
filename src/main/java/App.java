import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Publisher publisher = new Publisher();
        publisher.setName("Orbit Books");
        publisher.setEstablishment(1991);
        publisher.setAddress("123 Publisher St.");
        entityManager.persist(publisher);

        Author author = new Author();
        author.setName("Andrzej Sapkowski");
        author.setBirthDate(1948);
        author.setCountry("Poland");
        entityManager.persist(author);

        Category category = new Category();
        category.setName("Fantasy");
        category.setDescription("Fantasy books with magical elements.");
        entityManager.persist(category);

        Book book = new Book();
        book.setName("The Last Wish");
        book.setPublicationYear(1993);
        book.setStock(5);
        book.setPublisher(publisher);
        book.setAuthor(author);
        book.getCategories().add(category);
        entityManager.persist(book);

        BookBorrowing borrowing = new BookBorrowing();
        borrowing.setName("Jane Smith");
        borrowing.setBorrowingDate(new Date());
        borrowing.setBook(book);
        entityManager.persist(borrowing);

        book.getBorrowings().add(borrowing);

        entityTransaction.commit();
    }
}
