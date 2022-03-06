package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.exception.NotRegisteredException;
import ru.netology.exception.AlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game();

    private final Player player1 = new Player(13, "Hero", 150);
    private final Player player2 = new Player(54, "Weak", 1);
    private final Player player3 = new Player(23, "Strong", 150);
    private final Player player4 = new Player(4, "Super hero", 300);
    private final Player player5 = new Player(60, "Hero", 250);

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
    void registerExistingNamePlayers() {
        game.register(player1);
        game.register(player2);

        assertThrows(AlreadyExistsException.class, () -> game.register(player5));
    }

    @Test
    void roundPlayerStrengthEqual() {
        game.register(player1);
        game.register(player3);

        int expected = 0;
        int actual = game.round("Hero", "Strong");

        assertEquals(expected, actual);
    }

    @Test
    void roundPlayer1Stronger() {
        game.register(player4);
        game.register(player2);

        int expected = 1;
        int actual = game.round("Super hero", "Weak");

        assertEquals(expected, actual);
    }

    @Test
    void roundPlayer2Stronger() {
        game.register(player2);
        game.register(player3);

        int expected = 2;
        int actual = game.round("Weak", "Strong");

        assertEquals(expected, actual);
    }

    @Test
    void roundPlayerNotRegistered() {
        game.register(player5);
        game.register(player2);

        assertThrows(NotRegisteredException.class, () -> game.round("Hero", "Unknown"));
    }
}