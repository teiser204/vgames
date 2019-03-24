package gr.artibet.vgames.models;

public class Platform {

    // Class members

    private int id;
    private String desc;

    // Constructors

    public Platform(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

}
