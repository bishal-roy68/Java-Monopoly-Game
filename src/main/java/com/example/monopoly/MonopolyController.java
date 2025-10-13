package com.example.monopoly;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class MonopolyController {

    @FXML
    private Button rollButton, buyButton, houseButton, saveButton, loadButton, chanceButton, chestButton;

    @FXML
    private Label gameStatus;

    private int playerMoney = 1500;
    private int position = 0;
    private int houses = 0;
    private boolean hasHotel = false;
    private Random random = new Random();

    @FXML
    protected void onRollClicked() {
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        position = (position + dice1 + dice2) % 20;
        gameStatus.setText("You rolled " + (dice1 + dice2) + ". Now on space " + position);
    }

    @FXML
    protected void onBuyClicked() {
        if (playerMoney >= 200) {
            playerMoney -= 200;
            gameStatus.setText("You bought a property. Money left: $" + playerMoney);
        } else {
            gameStatus.setText("Not enough money to buy.");
        }
    }

    @FXML
    protected void onHouseClicked() {
        if (houses < 4 && playerMoney >= 100) {
            houses++;
            playerMoney -= 100;
            gameStatus.setText("Bought house #" + houses + ". Money left: $" + playerMoney);
        } else if (houses == 4 && !hasHotel && playerMoney >= 200) {
            hasHotel = true;
            playerMoney -= 200;
            gameStatus.setText("Built a hotel. Money left: $" + playerMoney);
        } else {
            gameStatus.setText("Can't buy more or not enough money.");
        }
    }

    @FXML
    protected void onSaveClicked() {
        try (FileWriter writer = new FileWriter("save.txt")) {
            writer.write(playerMoney + "," + position + "," + houses + "," + hasHotel);
            gameStatus.setText("Game saved.");
        } catch (Exception e) {
            gameStatus.setText("Error saving game.");
        }
    }

    @FXML
    protected void onLoadClicked() {
        try (Scanner scanner = new Scanner(new File("save.txt"))) {
            if (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                playerMoney = Integer.parseInt(data[0]);
                position = Integer.parseInt(data[1]);
                houses = Integer.parseInt(data[2]);
                hasHotel = Boolean.parseBoolean(data[3]);
                gameStatus.setText("Game loaded. Money: $" + playerMoney + ", Position: " + position);
            }
        } catch (Exception e) {
            gameStatus.setText("No saved game found.");
        }
    }

    @FXML
    protected void onChanceClicked() {
        String[] cards = {
                "Advance to GO. Collect $200",
                "Bank error in your favor. Collect $100",
                "Pay fine of $50",
                "Go to Jail. Pay $50"
        };

        int pick = random.nextInt(cards.length);
        String card = cards[pick];

        if (card.contains("Collect $200")) playerMoney += 200;
        else if (card.contains("Collect $100")) playerMoney += 100;
        else if (card.contains("Pay $50")) playerMoney -= 50;

        gameStatus.setText("Chance: " + card + " | Money: $" + playerMoney);
    }

    @FXML
    protected void onChestClicked() {
        String[] cards = {
                "Doctor's fee. Pay $50",
                "You inherit $100",
                "You get $45 from stock sale",
                "Receive $25 consulting fee"
        };

        int pick = random.nextInt(cards.length);
        String card = cards[pick];

        if (card.contains("Pay $50")) playerMoney -= 50;
        else if (card.contains("inherit $100")) playerMoney += 100;
        else if (card.contains("get $45")) playerMoney += 45;
        else if (card.contains("Receive $25")) playerMoney += 25;

        gameStatus.setText("Community Chest: " + card + " | Money: $" + playerMoney);
    }
}

