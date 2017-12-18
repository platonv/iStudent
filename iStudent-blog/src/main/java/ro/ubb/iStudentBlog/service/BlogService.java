package ro.ubb.iStudentBlog.service;

import org.springframework.stereotype.Service;
import ro.ubb.iStudentBlog.DTO.AddRatingDto;
import ro.ubb.iStudentBlog.DTO.BlogPieceDto;
import ro.ubb.iStudentBlog.DTO.CreateBlogPieceDto;
import ro.ubb.iStudentBlog.DTO.UpdateBlogPieceDto;
import ro.ubb.iStudentBlog.mapper.BlogPieceMapper;
import ro.ubb.iStudentBlog.mapper.CreateBlogPieceMapper;
import ro.ubb.iStudentBlog.model.BlogPiece;
import ro.ubb.iStudentBlog.model.Rating;
import ro.ubb.iStudentBlog.repository.BlogRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final RatingService ratingService;

    public BlogService(final BlogRepository blogRepository, final RatingService ratingService) {
        this.blogRepository = blogRepository;
        this.ratingService = ratingService;
        addDataToDb(blogRepository);
    }

    private void addDataToDb(final BlogRepository blogRepository) {
        final Rating rating = this.ratingService.save(0);
        final Rating rating1 = this.ratingService.save(7);
        blogRepository.save(BlogPiece.builder().content("good news").user("teacher").ratings(Arrays.asList(rating)).build());
        blogRepository.save(BlogPiece.builder().content("bad news").user("teacher").ratings(Arrays.asList(rating1)).build());
    }

    public BlogPieceDto getBlogPiece(final String id) {
        return BlogPieceMapper.toDto(this.blogRepository.findOne(id));
    }

    public String addBlogPiece(final CreateBlogPieceDto createBlogPieceDto) {
        final BlogPiece blogPiece = CreateBlogPieceMapper.toEntity(createBlogPieceDto);
        blogPiece.setRatings(new ArrayList<>());
        return blogRepository.save(blogPiece).getUuid();
    }

    public void addRatingToBlogPiece(final AddRatingDto addRatingDto) {
        final BlogPiece blogPiece = this.blogRepository.findOne(addRatingDto.getBlogPieceId());
        final Rating rating = this.ratingService.save(addRatingDto.getRating());
        blogPiece.getRatings().add(rating);
        this.blogRepository.save(blogPiece);
    }

    public List<BlogPieceDto> findAll() {
        return BlogPieceMapper.toDtos(blogRepository.findAll());
    }

    public void removeBlogPiece(final String id) {
        this.blogRepository.delete(id);
    }

    public void updateBlogPiece(final UpdateBlogPieceDto updateBlogPieceDto) {
        final BlogPiece blogPiece = this.blogRepository.findOne(updateBlogPieceDto.getUuid());
        blogPiece.setContent(updateBlogPieceDto.getContent());
        this.blogRepository.save(blogPiece);
    }

}
