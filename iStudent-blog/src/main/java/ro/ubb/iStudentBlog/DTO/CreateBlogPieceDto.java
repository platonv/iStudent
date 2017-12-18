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

    private String content;
    //here it should be a User class but that will change once auth is finished
    private String user;

}
