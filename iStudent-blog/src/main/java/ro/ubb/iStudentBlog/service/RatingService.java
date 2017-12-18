package ro.ubb.iStudentBlog.service;

import org.springframework.stereotype.Service;
import ro.ubb.iStudentBlog.model.Rating;
import ro.ubb.iStudentBlog.repository.RatingRepository;

/**
 * @author ciprian.mosincat on 12/18/2017.
 */
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(final RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    Rating save(final double rate) {
        final Rating rating = Rating.builder()
                .rate(rate).build();
        return this.ratingRepository.save(rating);
    }

}
