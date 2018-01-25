package ro.ubb.iStudentBlog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "blog")
public class Blog{

    @Id
    private String uuid;
    private String owner;
    private List<String> collaborators;
    private List<BlogPiece> blogPieces;

    private Blog(BlogBuilder builder){
        this.uuid = builder.uuid;
        this.owner = builder.owner;
        this.collaborators = builder.collaborators;
        this.blogPieces = builder.blogPieces;
    }

    public static BlogBuilder builder(){
        return new BlogBuilder();
    }


    public static class BlogBuilder{
        private String uuid;
        private String owner;
        private List<String> collaborators;
        private List<BlogPiece> blogPieces;

        public Blog build(){
            return new Blog(this);
        }

        public BlogBuilder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }

        public BlogBuilder owner(String owner){
            this.owner = owner;
            return this;
        }

        public BlogBuilder collaborators(List<String> collaborators){
            this.collaborators = collaborators;
            return this;
        }

        public BlogBuilder blogPieces(List<BlogPiece> blogPieces){
            this.blogPieces = blogPieces;
            return this;
        }

    }
}
