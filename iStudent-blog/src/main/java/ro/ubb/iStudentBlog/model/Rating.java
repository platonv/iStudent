package ro.ubb.iStudentBlog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ciprian.mosincat on 12/18/2017.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ratings")
public class Rating {

    @Id
    private String uuid;
    private double rate;

    private Rating(RatingBuilder builder){
        this.uuid = builder.uuid;
        this.rate = builder.rate;
    }

    public static RatingBuilder builder(){
        return new RatingBuilder();
    }



    public static class RatingBuilder{
        private String uuid;
        private double rate;

        public Rating build(){
            return new Rating(this);
        }

        public RatingBuilder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }

        public RatingBuilder rate(double rate){
            this.rate = rate;
            return this;
        }
    }
}
