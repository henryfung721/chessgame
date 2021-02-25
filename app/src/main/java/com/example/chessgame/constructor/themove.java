package com.example.chessgame.constructor;
public  final class themove {
    /*
    constructor to store the previous move

    meaning of the variables:
    first position(the position where the piece is not moved) :(firstpositionx,firstpositiony)
    second position(the position where the piece are moving to) :(secondpositionx,secondpositiony)
    moving player(the player who move the piece): player
    */
    private int player;
    private String firstchesstype;
    private int firstpositionx ;
    private int firstpositiony;
    private String secondchesstype;
    private int secondpositionx ;
    private int secondpositiony;
    private boolean castling;

    public themove(int player,String firstchesstype,int firstpositionx,int firstpositiony,String secondchesstype,int secondpositionx,int secondpositiony,boolean castling) {
        this.player=player;
        this.firstchesstype=firstchesstype;
        this.firstpositionx = firstpositionx;
        this.firstpositiony = firstpositiony;
        this.secondchesstype=secondchesstype;
        this.secondpositionx = secondpositionx;
        this.secondpositiony = secondpositiony;
        this.castling=castling;
    }
    public int getPlayer() {
        return player;
    }
    public String getFirstchesstype(){
        return firstchesstype;
    }
    public String getSecondchesstype(){
        return secondchesstype;
    }
    public int getFirstpositionx() {
        return firstpositionx;
    }
    public int getFirstpositiony() {
        return firstpositiony;
    }
    public int getSecondpositionx() {
        return secondpositionx;
    }
    public int getSecondpositiony() {
        return secondpositiony;
    }
    public boolean isCastling() {
        return castling;
    }
}
