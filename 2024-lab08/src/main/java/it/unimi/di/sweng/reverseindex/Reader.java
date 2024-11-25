package it.unimi.di.sweng.reverseindex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {

    private final List<String> state;
    private final boolean noPunctuation;
    private final boolean fromFile;

    private Reader(Builder builder) {
        state = new ArrayList<>();
        noPunctuation = builder.noPunctuation;
        fromFile = builder.fromFile;
    }

    public void read(String input) throws IOException {
        String content;
        if (!fromFile) content = input;
        else content = Files.readString(Path.of("src/test/java/it/unimi/di/sweng/reverseindex/files", input));
        if (!noPunctuation) state.addAll(Arrays.stream(content.split("\\s+")).toList());
        else state.addAll(Arrays.stream(content.replaceAll("\\p{Punct}", "").split("\\s+")).toList());
    }

    public static class Builder {
        private boolean noPunctuation = false;
        private boolean fromFile = false;

        public Builder withNoPunctuation(boolean np) {
            noPunctuation = np;
            return this;
        }

        public Builder withFileReader(boolean ff) {
            fromFile = ff;
            return this;
        }

        public Reader build() {
            return new Reader(this);
        }

    }
}
