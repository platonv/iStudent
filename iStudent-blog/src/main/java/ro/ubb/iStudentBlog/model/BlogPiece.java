package ro.ubb.iStudentBlog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "blogpiece")
public class BlogPiece {

    @Id
    private String uuid;
    private String content;
    //here it should be a User class but that will change once auth is finished
    private String user;
    private List<Rating> ratings;

    private BlogPiece(BlogPieceBuilder builder){
        this.uuid = builder.uuid;
        this.content = builder.content;
        this.user = builder.user;
        this.ratings = builder.ratings;
    }

    public static BlogPieceBuilder builder(){
        return new BlogPieceBuilder();
    }


    public static class BlogPieceBuilder{
        private String uuid;
        private String content;
        private String user;
        private List<Rating> ratings;

        public BlogPiece build(){
            return new BlogPiece(this);
        }

        public BlogPieceBuilder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }

        public BlogPieceBuilder content(String content){
            this.content = content;
            return this;
        }

        public BlogPieceBuilder user(String user){
            this.user = user;
            return this;
        }

        public BlogPieceBuilder ratings(List<Rating> ratings){
            this.ratings = ratings;
            return this;
        }
    }
}
