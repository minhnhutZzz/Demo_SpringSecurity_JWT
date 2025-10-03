package LTW3.Dao.Impl;

import LTW3.Entity.Book;
import LTW3.Entity.Rating;
import LTW3.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReviewDaoImpl {

    private EntityManager entityManager;

    public ReviewDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Lấy danh sách reviews của một cuốn sách
    public List<Rating> getRatingsByBook(Book book) {
        // Truy vấn sử dụng JPQL để lấy các đánh giá và thông tin người đánh giá
        TypedQuery<Rating> query = entityManager.createQuery(
            "SELECT r FROM Rating r " +
            "JOIN r.book b " +
            "JOIN r.user u " +
            "WHERE b.bookid = :bookid ORDER BY r.rating DESC", Rating.class);
        query.setParameter("bookid", book.getBookid());
        return query.getResultList();
    }

    // Thêm một đánh giá mới vào cơ sở dữ liệu
    public void addRating(Rating rating) {
        entityManager.getTransaction().begin();
        entityManager.persist(rating);
        entityManager.getTransaction().commit();
    }
}
