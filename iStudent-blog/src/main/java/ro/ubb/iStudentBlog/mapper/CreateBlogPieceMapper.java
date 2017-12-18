package ro.ubb.iStudentBlog.mapper;

import lombok.experimental.UtilityClass;
import ro.ubb.iStudentBlog.DTO.CreateBlogPieceDto;
import ro.ubb.iStudentBlog.model.BlogPiece;

/**
 * @author ciprian.mosincat on 12/18/2017.
 */
@UtilityClass
public class CreateBlogPieceMapper {

    public BlogPiece toEntity(final CreateBlogPieceDto dto) {
        return dto == null ? null : BlogPiece.builder()
                .content(dto.getContent())
                .user(dto.getUser())
                .build();
    }

}
