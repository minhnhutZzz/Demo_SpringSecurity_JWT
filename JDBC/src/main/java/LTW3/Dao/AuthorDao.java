package LTW3.Dao;

import java.util.List;

import LTW3.Model.AuthorModel;




public interface AuthorDao {

	AuthorModel getAuthorById(int authorId);

	void deleteAuthor(int authorId);

	void updateAuthor(AuthorModel author);

	void addAuthor(AuthorModel author);

	int getTotalAuthors();

	List<AuthorModel> getAuthorsPaginated(int start, int recordsPerPage);

	List<AuthorModel> getAllAuthors();



}
