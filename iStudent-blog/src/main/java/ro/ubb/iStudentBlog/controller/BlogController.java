package ro.ubb.iStudentBlog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getBlogs() {
        return new ResponseEntity<>(this.blogService.getAllBlogs(),HttpStatus.OK);
    }

    @GetMapping("/blog")
    public ResponseEntity getBlog(@RequestParam final String id) {
        return new ResponseEntity<>(this.blogService.getBlog(id),HttpStatus.OK);
    }

    @PostMapping("/blog")
    public ResponseEntity createBlog(@RequestBody final CreateBlogDto createBlogDto) {
        return new ResponseEntity<>(this.blogService.createBlog(createBlogDto),HttpStatus.OK);
    }

    @GetMapping("/blogPieces")
    public ResponseEntity getBlogPieces(@RequestParam final String blogId) {
        return new ResponseEntity<>(this.blogService.getPiecesForBlog(blogId), HttpStatus.OK);
    }

    @PostMapping("/addCollaborator")
    public ResponseEntity addCollaborator(@RequestParam final String blogId,@RequestParam final String collaborator){
        return new ResponseEntity<>(this.blogService.addCollaboratorToBlog(collaborator,blogId),HttpStatus.OK);
    }

    @PostMapping("/removeCollaborator")
    public ResponseEntity removeCollaborator(@RequestParam final String blogId,@RequestParam final String collaborator){
        return new ResponseEntity<>(this.blogService.removeCollaboratorToBlog(collaborator,blogId),HttpStatus.OK);
    }

}
