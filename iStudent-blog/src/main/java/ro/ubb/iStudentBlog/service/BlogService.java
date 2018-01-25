package ro.ubb.iStudentBlog.service;

import org.springframework.stereotype.Service;
import ro.ubb.iStudentBlog.DTO.AddRatingDto;
import ro.ubb.iStudentBlog.DTO.BlogPieceDto;
import ro.ubb.iStudentBlog.DTO.CreateBlogPieceDto;
import ro.ubb.iStudentBlog.DTO.UpdateBlogPieceDto;
import ro.ubb.iStudentBlog.mapper.BlogPieceMapper;
import ro.ubb.iStudentBlog.mapper.CreateBlogPieceMapper;
import ro.ubb.iStudentBlog.model.Blog;
import ro.ubb.iStudentBlog.model.BlogPiece;
import ro.ubb.iStudentBlog.model.Rating;
import ro.ubb.iStudentBlog.repository.BlogRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Cata on 12/6/2017.
 */
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(final BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
        addDataToDb(blogRepository);
    }

    private void addDataToDb(final BlogRepository blogRepository) {
//        final Rating rating = this.ratingService.save(0);
//        final Rating rating1 = this.ratingService.save(7);
//        blogRepository.save(BlogPiece.builder().content("good news").user("teacher").ratings(Arrays.asList(rating)).build());
//        blogRepository.save(BlogPiece.builder().content("bad news").user("teacher").ratings(Arrays.asList(rating1)).build());
    }

    public BlogPieceDto getBlogPiece(final String blogId, final String blogPieceId) {
        final Blog blog = blogRepository.findOne(blogId);
        BlogPiece blogPiece = blog.getBlogPieces().stream()
                .filter(b -> b.getUuid().equals(blogPieceId)).findFirst().get();
        return BlogPieceMapper.toDto(blogPiece);
    }

    public String addBlogPiece(final CreateBlogPieceDto createBlogPieceDto) {
        final Blog blog = blogRepository.findOne(createBlogPieceDto.getBlogId());
        final BlogPiece blogPiece = CreateBlogPieceMapper.toEntity(createBlogPieceDto);
            blogPiece.setRatings(new ArrayList<>());
        blog.getBlogPieces().add(blogPiece);
        blogRepository.save(blog);
        return blogPiece.getUuid();
    }

    public void addRatingToBlogPiece(final AddRatingDto addRatingDto) {
        Blog blog = blogRepository.findOne(addRatingDto.getBlogId());
        blog.getBlogPieces().stream()
                .filter(b -> b.getUuid().equals(addRatingDto.getBlogPieceId())).findFirst()
                .ifPresent(blogPiece -> blogPiece.getRatings().add(
                        Rating.builder().uuid(UUID.randomUUID().toString()).rate(addRatingDto.getRating()).build()));
    }

    public List<BlogPieceDto> getPiecesForBlog(String blogId) {
        Blog blog = blogRepository.findOne(blogId);
        return BlogPieceMapper.toDtos(blog.getBlogPieces());
    }

    public void removeBlogPiece(final String id) {
        this.blogRepository.delete(id);
    }

    public BlogPiece updateBlogPiece(final UpdateBlogPieceDto updateBlogPieceDto) {
        throw new NotImplementedException();
    }

}
