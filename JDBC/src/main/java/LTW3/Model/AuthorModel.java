package LTW3.Model;

import java.io.Serializable;
import java.util.Date;

public class AuthorModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int authorId;  // ID tác giả
    private String authorName;  // Tên tác giả
    private Date dateOfBirth;  // Ngày sinh

    // Default constructor
    public AuthorModel() {
        super();
    }

    // Getters and setters
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "AuthorModel [authorId=" + authorId + ", authorName=" + authorName + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
