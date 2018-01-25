package ro.ubb.iStudentBlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.iStudentBlog.model.Blog;

/**
 * Created by Cata on 12/6/2017.
 */
public interface BlogRepository extends MongoRepository<Blog, String> {
}
