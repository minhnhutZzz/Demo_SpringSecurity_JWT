package LTW3.Service.Impl;

import java.util.List;

import LTW3.Dao.RatingDao_23110282;
import LTW3.Model.RatingModel_23110282;
import LTW3.Service.RatingService_23110282;

public class RatingServiceImpl_23110282 implements RatingService_23110282 {
    private RatingDao_23110282 ratingDao;

    public RatingServiceImpl_23110282(RatingDao_23110282 ratingDao) {
        this.ratingDao = ratingDao;
    }

  
    @Override
	public boolean addRating(RatingModel_23110282 rating) {
        try {
            ratingDao.addRating(rating);
            return true;   // Trả về true nếu insert không nổ lỗi
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Nếu lỗi thì false
        }
    }

  
    @Override
	public List<RatingModel_23110282> getRatingsByBook(int bookId) {
        return ratingDao.getRatingsByBook(bookId);
    }
}
