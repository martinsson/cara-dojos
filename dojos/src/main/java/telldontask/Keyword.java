package telldontask;

public class Keyword {

    private final String searchTerm;
    private final String isPublished;

    public Keyword(String searchTerm, String isPublished) {
        this.searchTerm = searchTerm;
        this.isPublished = isPublished;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public String isPublished() {
        return isPublished;
    }
    
    @Override
    public String toString() {
        return searchTerm;
    }

}