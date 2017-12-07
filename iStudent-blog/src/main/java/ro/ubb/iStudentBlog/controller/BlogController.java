package ro.ubb.iStudentBlog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.iStudentBlog.DTO.BlogPieceDTO;
import ro.ubb.iStudentBlog.model.BlogPiece;
import ro.ubb.iStudentBlog.service.BlogService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cata on 12/6/2017.
 */
@RestController
@RequestMapping("/blogpiece")
public class BlogController {
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    BlogService blogService;

    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    public List<BlogPieceDTO> getAll(){
        log.trace("getting all BlogPieces");
        List<BlogPiece> blogPieces = blogService.findAll();
        log.trace("Got BlogPieces={}",blogPieces);
        return blogPieces.stream().map(BlogPieceDTO::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/createPiece",method = RequestMethod.POST)
    public void createPiece(@RequestBody BlogPieceDTO blogPieceDTO){
        log.trace("Adding BLogPiece={}",blogPieceDTO);
        blogService.addBlogPiece(blogPieceDTO.toPOJO());
        log.trace("Added BlogPiece!");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void removePiece(@RequestBody BlogPieceDTO blogPieceDTO){
        log.trace("Removing BLogPiece={}",blogPieceDTO);
        blogService.removeBlogPiece(blogPieceDTO.toPOJO());
        log.trace("Removed BlogPiece!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updatePiece(@RequestBody BlogPieceDTO blogPieceDTO){
        log.trace("Updating BLogPiece={}",blogPieceDTO);
        blogService.updateBlogPiece(blogPieceDTO.toPOJO());
        log.trace("Updated BlogPiece!");
    }

}
