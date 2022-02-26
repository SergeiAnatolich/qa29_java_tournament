package ru.netology.repository;

import ru.netology.domain.Player;
import ru.netology.exception.NotRegisteredException;

import java.util.HashMap;

public class Game {

    private final HashMap<String, Player> registerPlayers = new HashMap<>();

    public void register(Player player) {
        registerPlayers.put(player.getName(), player);
    }

    public HashMap<String, Player> getRegisterPlayers() {
        return registerPlayers;
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = new Player();
        Player player2 = new Player();
        for (String playerName : registerPlayers.keySet()) {
            if (playerName.equals(playerName1)) {
                player1 = registerPlayers.get(playerName);
            }
            if (playerName.equals(playerName2)) {
                player2 = registerPlayers.get(playerName);
            }
        }
        if (player1.getName() != null && player2.getName() != null) {
            if (player1.getStrength() > player2.getStrength()) {
                return 1;
            } else if (player1.getStrength() < player2.getStrength()) {
                return 2;
            } else {
                return 0;
            }
        } else {
            throw new NotRegisteredException("Player not registered");
        }
    }
}