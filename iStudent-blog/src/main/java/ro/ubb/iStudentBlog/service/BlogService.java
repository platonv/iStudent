package ro.ubb.iStudentBlog.service;

import org.springframework.stereotype.Service;
import ro.ubb.iStudentBlog.model.BlogPiece;
import ro.ubb.iStudentBlog.repository.BlogRepository;

import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void addBlogPiece(BlogPiece blogPiece){
        blogRepository.save(blogPiece);
    }

    public List<BlogPiece> findAll() {
        return  blogRepository.findAll();
    }

    public void removeBlogPiece(BlogPiece blogPiece){
        blogRepository.delete(blogPiece);
    }

    public void updateBlogPiece(BlogPiece blogPiece){
        blogRepository.save(blogPiece);
    }
}
