package it.unimi.di.sweng.reverseindex;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@Timeout(2)
public class TestReader {

    @ParameterizedTest
    @CsvSource(textBlock = """
            'Sopra la panca, la capra campa!'
            """)
    void testSimpleReader(String input) throws IOException {
        Reader r = (new Reader.Builder().withNoPunctuation(false)).build();
        r.read(input);
        assertThat(r).extracting("state", as(LIST)).containsExactlyInAnyOrderElementsOf(
                List.of("Sopra", "la", "panca,", "la", "capra", "campa!"));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            'Sopra la panca, la capra campa!'
            """)
    void testNoPunctuationReader(String input) throws IOException {
        Reader r = (new Reader.Builder().withNoPunctuation(true)).build();
        r.read(input);
        assertThat(r).extracting("state", as(LIST)).containsExactlyInAnyOrderElementsOf(
                List.of("Sopra", "la", "panca", "la", "capra", "campa"));
    }

    @Test
    void testFromFileReader() throws IOException {
        Reader r = (new Reader.Builder().withNoPunctuation(true).withFileReader(true)).build();
        r.read("input.txt");
        assertThat(r).extracting("state", as(LIST)).containsExactlyInAnyOrderElementsOf(
                List.of("Sopra", "la", "panca", "la", "capra", "campa"));
    }
}
