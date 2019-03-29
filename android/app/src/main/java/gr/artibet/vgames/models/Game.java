package gr.artibet.vgames.models;

import java.util.ArrayList;

public class Game {

    // Class members
    private int id;
    private String title;
    private String desc;
    private int ryear;
    private int rmonth;
    private int rday;
    private double price;
    private double rating;
    private String url;
    private String image;

    private Company company;
    private ArrayList<Feature> featureList;
    private ArrayList<Platform> platformList;
    private ArrayList<Language> languageList;
    private ArrayList<Genre> genreList;
    private ArrayList<Gallery> galleryList;

    // Defafult contructor
    public Game() {

    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRyear() {
        return ryear;
    }

    public void setRyear(int ryear) {
        this.ryear = ryear;
    }

    public int getRmonth() {
        return rmonth;
    }

    public void setRmonth(int rmonth) {
        this.rmonth = rmonth;
    }

    public int getRday() {
        return rday;
    }

    public void setRday(int rday) {
        this.rday = rday;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Feature> getFeatureList() {
        return featureList;
    }

    public void setFeatureList(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }

    public ArrayList<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(ArrayList<Platform> platformList) {
        this.platformList = platformList;
    }

    public ArrayList<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(ArrayList<Language> languageList) {
        this.languageList = languageList;
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    public ArrayList<Gallery> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(ArrayList<Gallery> galleryList) {
        this.galleryList = galleryList;
    }
}
