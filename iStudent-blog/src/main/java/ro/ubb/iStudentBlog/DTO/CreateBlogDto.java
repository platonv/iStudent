package ro.ubb.iStudentBlog.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ciprian.mosincat on 1/25/2018.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlogDto {

    String owner;
    List<String> collaborators;

}
