package ro.ubb.iStudentBlog.mapper;

import lombok.experimental.UtilityClass;
import ro.ubb.iStudentBlog.DTO.BlogPieceDto;
import ro.ubb.iStudentBlog.model.BlogPiece;
import ro.ubb.iStudentBlog.model.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * @author ciprian.mosincat on 12/18/2017.
 */
@UtilityClass
public class BlogPieceMapper {

    public BlogPieceDto toDto(final BlogPiece entity) {
        return entity == null ? null : BlogPieceDto.builder()
                .uuid(entity.getUuid())
                .content(entity.getContent())
                .rating(computeRating(entity.getRatings()))
                .user(entity.getUser()).build();
    }

    public List<BlogPieceDto> toDtos(final List<BlogPiece> entities) {
        return entities == null ? new ArrayList<>() : entities.stream()
                .map(BlogPieceMapper::toDto)
                .collect(Collectors.toList());
    }

    private Double computeRating(final List<Rating> ratings) {
        final OptionalDouble average = ratings
                .stream()
                .mapToDouble(Rating::getRate)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

}
