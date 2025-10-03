package LTW3.Dao.Impl;


import LTW3.Dao.BookDao;
import LTW3.Entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookDaoImpl implements BookDao {

    private EntityManager entityManager;

    public BookDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Truy vấn tất cả sách
    @Override
	public List<Book> getAllBooks() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    // Truy vấn sách theo ID
    @Override
	public Book getBookById(int bookid) {
        return entityManager.find(Book.class, bookid);
    }

    // Truy vấn sách theo ISBN
    @Override
	public Book getBookByIsbn(int isbn) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }
    
    @Override
	public void addBook(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }	
    
    
  
    
    @Override
	public void updateBook(Book book) {
        entityManager.getTransaction().begin();
        entityManager.merge(book);
        entityManager.getTransaction().commit();
    }
    
    @Override
	public void deleteBook(int bookid) {
        Book book = entityManager.find(Book.class, bookid);
        if (book != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(book);
            entityManager.getTransaction().commit();
        }
    }
    
 // Lấy sách theo phân trang
    @Override
	public List<Book> getBooksPaginated(int start, int recordsPerPage) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        query.setFirstResult(start); // Vị trí bắt đầu
        query.setMaxResults(recordsPerPage); // Số lượng sách mỗi trang
        return query.getResultList();
    }

    // Lấy tổng số sách
    @Override
	public int getTotalBooks() {
        String jpql = "SELECT COUNT(b) FROM Book b";
        Query query = entityManager.createQuery(jpql);
        long count = (long) query.getSingleResult();
        return (int) count;
    }

}
