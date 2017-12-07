package ro.ubb.iStudentBlog.DTO;

import ro.ubb.iStudentBlog.model.BlogPiece;

import java.util.UUID;

public class BlogPieceDTO {

    private String content;
    //here it should be a User class but that will change once auth is finished
    private String user;
    private Integer rating;

    public BlogPieceDTO() {
    }

    public BlogPieceDTO(BlogPiece blogPiece){
        content = blogPiece.getContent();
        user = blogPiece.getUser();
        rating = blogPiece.getRating();
    }

    public BlogPieceDTO(String content, String user, Integer rating) {
        this.content = content;
        this.user = user;
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public BlogPiece toPOJO(){
        return new BlogPiece(content,user,rating);
    }
}
