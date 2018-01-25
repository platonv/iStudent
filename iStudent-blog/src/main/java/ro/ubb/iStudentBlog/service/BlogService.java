package ro.ubb.iStudentBlog.service;

import org.springframework.stereotype.Service;
import ro.ubb.iStudentBlog.DTO.*;
import ro.ubb.iStudentBlog.exception.EntityNotFoundException;
import ro.ubb.iStudentBlog.mapper.BlogMapper;
import ro.ubb.iStudentBlog.mapper.BlogPieceMapper;
import ro.ubb.iStudentBlog.mapper.CreateBlogPieceMapper;
import ro.ubb.iStudentBlog.model.Blog;
import ro.ubb.iStudentBlog.model.BlogPiece;
import ro.ubb.iStudentBlog.model.Rating;
import ro.ubb.iStudentBlog.repository.BlogRepository;

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
        blogRepository.deleteAll();
        final Rating rating = Rating.builder().rate(1).build();
        final Rating rating1 = Rating.builder().rate(5).build();
        List<BlogPiece> blogPieces = new ArrayList<>();
        blogPieces.add(BlogPiece.builder().uuid(UUID.randomUUID().toString()).content("good news").user("teacher").ratings(Arrays.asList(rating)).build());
        blogPieces.add(BlogPiece.builder().uuid(UUID.randomUUID().toString()).content("bad news").user("teacher").ratings(Arrays.asList(rating1)).build());
        List<String> collabs = new ArrayList<>();
        collabs.add("Mihai");
        collabs.add("Ion");
        blogRepository.save(Blog.builder().blogPieces(blogPieces).collaborators(collabs).owner("Bogdan").uuid(UUID.randomUUID().toString()).build());
    }

    public List<BlogDto> getAllBlogs(){
        return this.blogRepository.findAll().stream()
                .map(BlogMapper::toDto)
                .collect(Collectors.toList());
    }

    public BlogDto getBlog(final String id) {
        return BlogMapper.toDto(this.blogRepository.findOne(id));
    }

    public BlogDto createBlog(final CreateBlogDto createBlogDto) {
        final Blog blog = Blog.builder().owner(createBlogDto.getOwner())
                .collaborators(createBlogDto.getCollaborators())
                .blogPieces(new ArrayList<>())
                .build();
        blog.getCollaborators().add(createBlogDto.getOwner());
        return BlogMapper.toDto(this.blogRepository.save(blog));

    }

    public BlogPieceDto getBlogPiece(final String blogId, final String blogPieceId) {
        final Blog blog = blogRepository.findOne(blogId);
        final BlogPiece blogPiece = blog.getBlogPieces().stream()
                .filter(b -> b.getUuid().equals(blogPieceId)).findFirst().get();
        return BlogPieceMapper.toDto(blogPiece);
    }

    public String addBlogPiece(final CreateBlogPieceDto createBlogPieceDto) {
        final Blog blog = blogRepository.findOne(createBlogPieceDto.getBlogId());
        final BlogPiece blogPiece = CreateBlogPieceMapper.toEntity(createBlogPieceDto);
        blogPiece.setUuid(UUID.randomUUID().toString());
        blogPiece.setRatings(new ArrayList<>());
        blog.getBlogPieces().add(blogPiece);
        blogRepository.save(blog);
        return blogPiece.getUuid();
    }

    public void addRatingToBlogPiece(final AddRatingDto addRatingDto) {
        final Blog blog = blogRepository.findOne(addRatingDto.getBlogId());
        blog.getBlogPieces().stream()
                .filter(b -> b.getUuid().equals(addRatingDto.getBlogPieceId())).findFirst()
                .ifPresent(blogPiece -> blogPiece.getRatings().add(
                        Rating.builder().uuid(UUID.randomUUID().toString()).rate(addRatingDto.getRating()).build()));
    }

    public List<BlogPieceDto> getPiecesForBlog(final String blogId) {
        final Blog blog = blogRepository.findOne(blogId);
        return BlogPieceMapper.toDtos(blog.getBlogPieces());
    }

    public void removeBlogPiece(final String id) {
        this.blogRepository.delete(id);
    }

    public BlogPiece updateBlogPiece(final String blogId, final UpdateBlogPieceDto updateBlogPieceDto) {
        final Blog blog = blogRepository.findOne(blogId);
        final Optional<BlogPiece> blogPiece = findBlogPiece(blog,updateBlogPieceDto.getUuid());
        if(blogPiece.isPresent())
            blogPiece.get().setContent(updateBlogPieceDto.getContent());
        else
            throw new EntityNotFoundException("BlogPiece does not exist!");
        blogRepository.save(blog);
        return blogPiece.get();
    }

    public BlogDto addCollaboratorToBlog(String collaborator,String blogId){
        final Blog blog = blogRepository.findOne(blogId);
        List<String> collaborators = blog.getCollaborators();
        collaborators.add(collaborator);
        blog.setCollaborators(collaborators);
        blogRepository.save(blog);
        return BlogMapper.toDto(blog);
    }

    public BlogDto removeCollaboratorToBlog(String collaborator,String blogId){
        final Blog blog = blogRepository.findOne(blogId);
        List<String> collaborators = blog.getCollaborators();
        collaborators.remove(collaborator);
        blog.setCollaborators(collaborators);
        blogRepository.save(blog);
        return BlogMapper.toDto(blog);
    }

    private Optional<BlogPiece> findBlogPiece(Blog blog, String blogPieceId){
        for(BlogPiece blogPiece : blog.getBlogPieces())
            if(blogPiece.getUuid().equals(blogPieceId))
                return Optional.of(blogPiece);
        return Optional.empty();
    }
}
