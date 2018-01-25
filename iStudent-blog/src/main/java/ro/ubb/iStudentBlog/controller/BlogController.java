package ro.ubb.iStudentBlog.controller;

import org.springframework.web.bind.annotation.*;
import ro.ubb.iStudentBlog.DTO.BlogDto;
import ro.ubb.iStudentBlog.DTO.BlogPieceDto;
import ro.ubb.iStudentBlog.DTO.CreateBlogDto;
import ro.ubb.iStudentBlog.service.BlogService;

import java.util.List;

/**
 * @author ciprian.mosincat on 1/25/2018.
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(final BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<BlogDto> getBlogs() {
        return this.blogService.getAllBlogs();
    }

    @GetMapping("/blog")
    public BlogDto getBlog(@RequestParam final String id) {
        return this.blogService.getBlog(id);
    }

    @PostMapping("/blog")
    public BlogDto createBlog(@RequestBody final CreateBlogDto createBlogDto) {
        return this.blogService.createBlog(createBlogDto);
    }

    @GetMapping("/blogPieces")
    public List<BlogPieceDto> getBlogPieces(@RequestParam final String blogId) {
        return this.blogService.getPiecesForBlog(blogId);
    }

}
