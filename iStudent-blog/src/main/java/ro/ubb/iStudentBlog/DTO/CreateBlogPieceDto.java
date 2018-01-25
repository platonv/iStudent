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
public class CreateBlogPieceDto {

    private String blogId;
    private String content;
    private String user;

}
