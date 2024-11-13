package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Timeout(2)
public class ForthInterpreterTest {
    private Interpreter interpreter;

    @BeforeEach
    public void setUp() {
        interpreter = new ForthInterpreter();
    }

    @Test
    void emptyStack() {
        interpreter.input("");
        assertThat(interpreter.toString()).isEqualTo("<- Top");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1 <- Top",
            "1 2, 1 2 <- Top"
    })
    void stackOfNumbers(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2, 1 2 <- Top",
            "'1\n2', 1 2 <- Top",
            "'1   2 \n3', 1 2 3 <- Top"
    })
    void numbersAndSpaces(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 +, 3 <- Top",
            "1 2 + 5 +, 8 <- Top"
    })
    void sumOperation(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 *, 2 <- Top",
            "1 2 * 5 *, 10 <- Top"
    })
    void mulOperation(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 -, -1 <- Top",
            "1 2 /, 0 <- Top"
    })
    void subDivOperations(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2+, Token error '2+'",
            "1 2 +5 +, Token error '+5'",
            "1 +, Stack Underflow"
    })
    void illegalArgs(String input, String error) {
        assertThatThrownBy(() -> interpreter.input(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(error);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 3 dup, 1 2 3 3 <- Top",
            "1 2 dup 3 dup, 1 2 2 3 3 <- Top"
    })
    void dupOperation(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 3 swap, 1 3 2 <- Top",
            "1 2 swap 3 swap, 2 3 1 <- Top"
    })
    void swapOperation(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 3 drop, 1 2 <- Top",
            "1 2 drop 3 4 drop, 1 3 <- Top"
    })
    void dropOperation(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 + 3 * 4 dup 5 + drop swap, 4 9 <- Top"
    })
    void advOperations(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 + 3 * drop swap"
    })
    void advOperationsException(String input) {
        assertThatThrownBy(() -> interpreter.input(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({
            ": raddoppia 2 * ; 5 raddoppia dup raddoppia, 10 20 <- Top"
    })
    void defineOperations(String input, String output) {
        interpreter.input(input);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "pippo, Token error 'pippo'",
            "1 2 pippo, Token error 'pippo'",
            "1 : raddoppia 2 * ; raddoppi raddoppia, Token error 'raddoppi'"
    })
    void defineOperationsException(String input, String error) {
        assertThatThrownBy(() -> interpreter.input(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(error);
    }
}