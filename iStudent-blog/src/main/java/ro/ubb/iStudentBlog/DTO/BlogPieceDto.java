package ro.ubb.iStudentBlog.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogPieceDto implements Serializable {

    private static final long serialVersionUID = -242972602789277084L;

    private String uuid;
    private String content;
    //here it should be a User class but that will change once auth is finished
    private String user;
    private Double rating;

}
