package ma.ilisi.asma.model;


public class Item {
    private String name;//Le nom du film (série)
    private String overview; //(Description)
    private String firstAirDate;//La date d'apparition
    private String imageUrl;///Le lien de l'image


    private double voteAverage;///Le score

///Le constructeur
    public Item(String name, String overview, String firstAirDate, double voteAverage, String imageUrl) {
        this.name = name;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
        this.voteAverage = voteAverage;
        this.imageUrl = imageUrl;
    }

///Getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
}
