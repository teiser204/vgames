package gr.artibet.vgames.models;

public class Gallery {

    // Class members
    private int id;
    private String image;

    // Constructor
    public Gallery(int id, String image) {
        this.id = id;
        this.image = image;
    }

    // Getters and setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
