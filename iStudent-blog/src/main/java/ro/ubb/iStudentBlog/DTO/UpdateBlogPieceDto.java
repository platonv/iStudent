package ro.ubb.iStudentBlog.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ciprian.mosincat on 12/18/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBlogPieceDto {

    private String uuid;
    private String content;

}
