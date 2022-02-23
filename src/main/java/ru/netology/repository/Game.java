package ru.netology.repository;

import ru.netology.domain.Player;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;

public class Game {

    private Collection<Player> registerPlayers = new ArrayList<>();

    public Collection<Player> getRegisterPlayers() {
        return registerPlayers;
    }

    public Game() {
    }

    public void register(Player player) {
        for (Player item : registerPlayers) {
            if (player.getId() == item.getId()) {
                throw new AlreadyExistsException("Player with id: " + player.getId() + " already exists");
            }
        }
        for (Player item : registerPlayers) {
            if (player.getName() == item.getName()) {
                throw new AlreadyExistsException("Player with name: " + player.getName() + " already exists");
            }
        }
        registerPlayers.add(player);
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = new Player();
        boolean registeredPlayer1 = false;
        Player player2 = new Player();
        boolean registeredPlayer2 = false;
        for (Player player : registerPlayers) {
            if (playerName1 == player.getName()) {
                registeredPlayer1 = true;
                player1 = player;
            }
            if (playerName2 == player.getName()) {
                registeredPlayer2 = true;
                player2 = player;
            }
        }
        if (registeredPlayer1 == registeredPlayer2) {
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
