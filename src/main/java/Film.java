public class Film {
    int filmId;
    String title;
    String description;

    // Did DTO first as you have the product that you ll be using (film is the product)

    public Film(int filmId, String title, String description) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
