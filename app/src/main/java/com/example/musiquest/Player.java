package com.example.musiquest;

public class Player {

    private String username;
    private String playerID;
    private String totalScore;
    private String rank;
    private String Cname;
    private String Cusername;
    private String CplayerID;
    private String CaccountType;
    private String CtotalScore;
    private String Crank;
   //leader board player
    public Player( String username, String playerID, String totalScore, String rank) {
        this.username = username;
        this.playerID = playerID;
        this.totalScore = totalScore;
        this.rank = rank;

    }
    public Player(String name, String username, String playerID, String accountType, String totalScore, String rank) {
        this.Cname = name;
        this.Cusername = username;
        this.CplayerID = playerID;
        this.CaccountType = accountType;
        this.CtotalScore = totalScore;
        this.Crank = rank;
    }


//current player info
    public String getName(){
        return Cname;
    };
    public String getUsernameName() {
        return Cusername;
    }
    public String getUserID() {
        return CplayerID;
    }
    public String getAccountType() {
        return CaccountType;
    }
    public String getScore() {
        return CtotalScore;
    }
    public String getRank() {
        return Crank;
    }

        //current player info

    public String leaderboardUsername() {
        return username;
    }
    public String leaderboardUserID() {
        return playerID;
    }
    public String leaderboardScore() {
        return totalScore;
    }
    public String leaderboardRank() {
        return rank;
    }

}
