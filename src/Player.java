// This code implement the player part of the game
public class Player {
    String name;
    int money;
    int position;
    boolean inJail;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.inJail = false;
    }
}

