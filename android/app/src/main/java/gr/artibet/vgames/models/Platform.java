package gr.artibet.vgames.models;

public class Platform {

    // Class members

    private int id;
    private int desc;

    // Constructors

    public Platform(int id, int desc) {
        this.id = id;
        this.desc = desc;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDesc() { return desc; }
    public void setDesc(int desc) { this.desc = desc; }

}
