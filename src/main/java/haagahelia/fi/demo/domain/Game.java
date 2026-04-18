package haagahelia.fi.demo.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title needed")
    private String title;

    @NotBlank(message = "Developer needed")
    private String developer;

    @NotBlank(message = "Category needed")
    private String category;

    private String review;

    @NotNull(message = "Year needed")
    private Integer releaseYear;

    @NotNull(message = "Rating needed")
    private Integer rating;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserReview> UserReviews = new ArrayList<>();

    public Game() {
    }

    public Game(String title, int releaseYear, String category, String developer, int rating, String review) {
        this.title = title;
        this.developer = developer;
        this.category = category;
        this.review = review;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<UserReview> getUserReviews() {
        return UserReviews;
    }

    public void setUserReviews(List<UserReview> userReviews) {
        UserReviews = userReviews;
    }

}
