package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game();

    private Player player1 = new Player(1, "Hero", 150);
    private Player player2 = new Player(2, "Weak", 1);
    private Player player3 = new Player(3, "Strong", 150);
    private Player player4 = new Player(4, "Super hero", 300);
    private Player player5 = new Player(5, "Hero", 250);
    private Player player6 = new Player(4, "Existing id", 250);


    @Test
    void registerOnePlayer() {
        game.register(player1);

        assertEquals(1, game.getRegisterPlayers().size());
    }

    @Test
    void registerManyPlayers() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);

        assertEquals(4, game.getRegisterPlayers().size());
    }

    @Test
    void registerExistingIdPlayers() {
        game.register(player1);
        game.register(player4);

        assertThrows(AlreadyExistsException.class, () -> game.register(player6));
    }

    @Test
    void registerExistingNamePlayers() {
        game.register(player1);
        game.register(player2);

        assertThrows(AlreadyExistsException.class, () -> game.register(player5));
    }

    @Test
    void round1() {
        game.register(player1);
        game.register(player3);

        int expected = 0;
        int actual = game.round("Hero", "Strong");

        assertEquals(expected, actual);
    }

    @Test
    void round2() {
        game.register(player4);
        game.register(player2);

        int expected = 1;
        int actual = game.round("Super hero", "Weak");

        assertEquals(expected, actual);
    }

    @Test
    void round3() {
        game.register(player2);
        game.register(player3);

        int expected = 2;
        int actual = game.round("Weak", "Strong");

        assertEquals(expected, actual);
    }

    @Test
    void round4() {
        game.register(player1);
        game.register(player4);

        int expected = 2;
        int actual = game.round("Hero", "Super hero");

        assertEquals(expected, actual);
    }

    @Test
    void round5() {
        game.register(player5);
        game.register(player2);

        assertThrows(NotRegisteredException.class, () -> game.round("Hero", "Unknown"));
    }
}