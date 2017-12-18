package ro.ubb.iStudentBlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.iStudentBlog.model.Rating;

import java.util.UUID;

/**
 * @author ciprian.mosincat on 12/18/2017.
 */
public interface RatingRepository extends MongoRepository<Rating, String> {
}
