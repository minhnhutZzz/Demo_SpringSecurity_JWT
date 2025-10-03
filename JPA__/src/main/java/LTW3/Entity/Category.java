package LTW3.Entity;

import java.io.Serializable;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;	

@Entity
@Table(name = "category")
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByUser", query = "SELECT c FROM Category c WHERE c.user.id = :userId")
})

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	private int categoryId;

	@Column(name = "categoryCode", columnDefinition = "NVARCHAR(255)")
	private String categoryCode;

	@Column(name = "categoryName", columnDefinition = "NVARCHAR(255)")
	private String categoryName;

	@Column(name = "images", columnDefinition = "NVARCHAR(MAX)")
	private String images;


	// Thêm thuộc tính user và ánh xạ đến bảng User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// Các getter và setter
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Các phương thức getter và setter còn lại...

	public Category() {
		super();
	}

	public Category(int categoryId, String categoryCode, String categoryName, String images, boolean status) {
		super();
		this.categoryId = categoryId;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.images = images;

	}

	// ===== GETTER & SETTER =====
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

}
