package LTW3.Service;

import java.util.List;

import LTW3.Model.AuthorModel;




public interface AuthorService {

	List<AuthorModel> getAllAuthors();

	List<AuthorModel> getAuthorsPaginated(int start, int recordsPerPage);

	int getTotalAuthors();

	void addAuthor(AuthorModel author);

	void updateAuthor(AuthorModel author);

	void deleteAuthor(int authorId);

	AuthorModel getAuthorById(int authorId);



}
