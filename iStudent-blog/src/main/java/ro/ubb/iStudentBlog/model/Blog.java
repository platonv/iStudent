package ro.ubb.iStudentBlog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "blog")
public class Blog{

    @Id
    private String uuid;
    private String owner;
    private List<String> collaborators;
    private List<BlogPiece> blogPieces;

}
