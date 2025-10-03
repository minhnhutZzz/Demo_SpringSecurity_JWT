package LTW3.Model;

import java.io.Serializable;
import java.util.Date;

public class UserModel_23110282 implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String email;
    private String fullname;
    private String passwd;
    private Date signupDate;
    private Date lastLogin;
    private boolean isAdmin;

    // Default constructor
    public UserModel_23110282() {
        super();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "UserModel [id=" + id + ", email=" + email + ", fullname=" + fullname + ", passwd=" + passwd
                + ", signupDate=" + signupDate + ", lastLogin=" + lastLogin + ", isAdmin=" + isAdmin + "]";
    }
}
