package gr.artibet.vgames.models;

import com.google.gson.annotations.SerializedName;

public class Language {

    // Class members

    private int id;
    private String desc;

    @SerializedName("total_games")
    private int totalGames;

    // Constructors

    public Language(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public int getTotalGames() { return totalGames; }
    public void setTotalGames(int n) { this.totalGames = n; }


    @Override
    public String toString() {
        return desc;
    }
}

