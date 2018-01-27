package ro.ubb.iStudentBlog.controller;

import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class BlogPieceController {

    private final BlogService blogService;

    public BlogPieceController(final BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity getBlogPiece(@RequestParam final String blogId, @RequestParam final String blogPieceId) {
        return new ResponseEntity<>(this.blogService.getBlogPiece(blogId, blogPieceId),HttpStatus.OK);
    }

    @PostMapping("/createPiece")
    public ResponseEntity createPiece(@RequestBody final CreateBlogPieceDto createBlogPieceDto) {
        return new ResponseEntity<>(blogService.addBlogPiece(createBlogPieceDto),HttpStatus.OK);
    }

    @PostMapping("/addRating")
    public ResponseEntity addRating(@RequestBody final AddRatingDto addRatingDto) {
        this.blogService.addRatingToBlogPiece(addRatingDto);
        return new ResponseEntity<>("Add successful",HttpStatus.OK);
    }

    @DeleteMapping("/deletePiece")
    public ResponseEntity removePiece(@RequestParam final String blogId, @RequestParam final String blogPieceId) {
        blogService.removeBlogPiece(blogId, blogPieceId);
        return new ResponseEntity<>("Remove successful",HttpStatus.OK);
    }

    @PostMapping("/updatePiece")
    public ResponseEntity updatePiece(@RequestBody final UpdateBlogPieceDto updateBlogPieceDto,@RequestBody String blogId) {
        return new ResponseEntity<>(blogService.updateBlogPiece(blogId,updateBlogPieceDto),HttpStatus.OK);
    }

}
