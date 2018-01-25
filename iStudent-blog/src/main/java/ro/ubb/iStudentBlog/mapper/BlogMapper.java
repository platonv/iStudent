package ro.ubb.iStudentBlog.mapper;

import lombok.experimental.UtilityClass;
import ro.ubb.iStudentBlog.DTO.BlogDto;
import ro.ubb.iStudentBlog.model.Blog;

/**
 * @author ciprian.mosincat on 1/25/2018.
 */
@UtilityClass
public class BlogMapper {

    public BlogDto toDto(final Blog blog) {
        return blog == null ? new BlogDto() : BlogDto.builder()
                .id(blog.getUuid())
                .owner(blog.getOwner())
                .collaborators(blog.getCollaborators())
                .blogPieceDtos(BlogPieceMapper.toDtos(blog.getBlogPieces())).build();
    }

}
