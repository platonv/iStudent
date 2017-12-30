package ro.ubb.iStudentBlog.controller;

import org.slf4j.event.Level;
import org.springframework.web.bind.annotation.*;
import ro.ubb.iStudentBlog.DTO.AddRatingDto;
import ro.ubb.iStudentBlog.DTO.BlogPieceDto;
import ro.ubb.iStudentBlog.DTO.CreateBlogPieceDto;
import ro.ubb.iStudentBlog.DTO.UpdateBlogPieceDto;
import ro.ubb.iStudentBlog.logging.LogMethodCall;
import ro.ubb.iStudentBlog.service.BlogService;

import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@LogMethodCall
@RestController
@RequestMapping("/blogpiece")
public class BlogController {

    private final BlogService blogService;

    public BlogController(final BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public BlogPieceDto getBlogPiece(@RequestParam final String id) {
        return this.blogService.getBlogPiece(id);
    }

    @GetMapping("/blogs")
    public List<BlogPieceDto> getAll() {
        return blogService.findAll();
    }

    @PostMapping("/createPiece")
    public String createPiece(@RequestBody final CreateBlogPieceDto createBlogPieceDto) {
        return blogService.addBlogPiece(createBlogPieceDto);
    }

    @PostMapping("/addRating")
    public void addRating(@RequestBody final AddRatingDto addRatingDto) {
        this.blogService.addRatingToBlogPiece(addRatingDto);
    }

    @DeleteMapping("/deletePiece")
    public void removePiece(@RequestParam final String id) {
        blogService.removeBlogPiece(id);
    }

    @PostMapping("/updatePiece")
    public void updatePiece(@RequestBody final UpdateBlogPieceDto updateBlogPieceDto) {
        blogService.updateBlogPiece(updateBlogPieceDto);
    }

}
