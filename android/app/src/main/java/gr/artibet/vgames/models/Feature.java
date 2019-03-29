package gr.artibet.vgames.models;

public class Feature {

    // Class members
    private int id;
    private String desc;

    // Constructor
    public Feature(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

}
