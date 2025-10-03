package LTW3.Dao.Impl;

import java.util.List;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.CategoryDao;
import LTW3.Entity.Category;
import LTW3.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void insert(Category category) {
		EntityManager enma = JPA_Config.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			if (category.getCategoryId() != 0) {
				// Nếu category đã có ID thì dùng merge
				enma.merge(category);
			} else {
				// Nếu là tạo mới thì dùng persist
				enma.persist(category);
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JPA_Config.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateid) throws Exception {
		EntityManager enma = JPA_Config.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category category = enma.find(Category.class, cateid);
			if (category != null) {
				enma.remove(category);
			} else {
				throw new Exception("Khong tim thay");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int categoryId) {
		EntityManager enma = JPA_Config.getEntityManager();
		Category category = enma.find(Category.class, categoryId);
		return category;

	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPA_Config.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();

	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		EntityManager enma = JPA_Config.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();

	}

	@Override
	public List<Category> findByCategoryname(String catname) {
		EntityManager emna = JPA_Config.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.catename LIKE :catname";
		TypedQuery<Category> query = emna.createQuery(jpql, Category.class);
		query.setParameter("catname", "%" + catname + "%");
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager emna = JPA_Config.getEntityManager();
		String jpql = "SELECT COUNT(c) FROM Category c";
		Query query = emna.createQuery(jpql);
		Long result = (Long) query.getSingleResult();
		return result.intValue();
	}

	@Override
	public List<Category> findByUser(User user) {
		EntityManager enma = JPA_Config.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.user = :user";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

}
