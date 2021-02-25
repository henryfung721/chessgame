package com.example.chessgame.constructor;

public final class game_record_firebase {
    private final String firebase_stored_position;
    private final String name,description;
    private final String move,time;
    private final String startchessboard,endchessboard;
    private final String black,white;

    public game_record_firebase(String firebase_stored_position,String name,String description,String move,String time,String startchessboard,String endchessboard,String black,String white) {
        this.firebase_stored_position=firebase_stored_position;
        this.name = name;
        this.description = description;
        this.move = move;
        this.time = time;
        this.startchessboard = startchessboard;
        this.endchessboard = endchessboard;
        this.black = black;
        this.white = white;


    }
    public String getFirebase_stored_position(){
        return firebase_stored_position;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getWhite() {
        return white;
    }
    public String getBlack() {
        return black;
    }
    public String getStartchessboard() {
        return startchessboard;
    }
    public String getEndchessboard() {
        return endchessboard;
    }
    public String getMove() {
        return move;
    }
    public String getTime() {
        return time;
    }
}