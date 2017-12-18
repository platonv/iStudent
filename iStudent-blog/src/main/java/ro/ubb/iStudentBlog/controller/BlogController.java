package ro.ubb.iStudentBlog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ro.ubb.iStudentBlog.DTO.AddRatingDto;
import ro.ubb.iStudentBlog.DTO.BlogPieceDto;
import ro.ubb.iStudentBlog.DTO.CreateBlogPieceDto;
import ro.ubb.iStudentBlog.DTO.UpdateBlogPieceDto;
import ro.ubb.iStudentBlog.service.BlogService;

import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@RestController
@RequestMapping("/blogpiece")
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    private final BlogService blogService;

    public BlogController(final BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public BlogPieceDto getBlogPiece(@RequestParam final String id){
        return this.blogService.getBlogPiece(id);
    }

    @GetMapping("/blogs")
    public List<BlogPieceDto> getAll() {
        log.trace("getting all BlogPieces");
        final List<BlogPieceDto> blogPieces = blogService.findAll();
        log.trace("Got BlogPieces={}", blogPieces);
        return blogPieces;
    }

    @PostMapping("/createPiece")
    public String createPiece(@RequestBody final CreateBlogPieceDto createBlogPieceDto) {
        log.trace("Adding BLogPiece={}", createBlogPieceDto);
        final String id = blogService.addBlogPiece(createBlogPieceDto);
        log.trace("Added BlogPiece!");
        return id;
    }

    @PostMapping("/addRating")
    public void addRating(@RequestBody final AddRatingDto addRatingDto) {
        log.trace("Add rating to blogPiece!");
        this.blogService.addRatingToBlogPiece(addRatingDto);
        log.trace("Added rating to blogPiece!");
    }

    @DeleteMapping("/deletePiece")
    public void removePiece(@RequestParam final String id) {
        log.trace("Removing BLogPiece={}", id);
        blogService.removeBlogPiece(id);
        log.trace("Removed BlogPiece!");
    }

    @PostMapping("/updatePiece")
    public void updatePiece(@RequestBody final UpdateBlogPieceDto updateBlogPieceDto) {
        log.trace("Updating BLogPiece={}", updateBlogPieceDto);
        blogService.updateBlogPiece(updateBlogPieceDto);
        log.trace("Updated BlogPiece!");
    }

}
