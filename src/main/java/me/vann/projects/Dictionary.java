package me.vann.projects;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 * Here are some of the sites I used to figure out JSON parsing:
 *      https://www.concretepage.com/jackson-api/read-write-json-using-jackson-objectmapper-jsonparser-jsongenerator-example
 *
 * Keeps track of the values of words, along with how many times the word was used.
 */
public class Dictionary {

    private final ObjectMapper mapper;
    private final Path path;

    public Dictionary(String path) {
        this.mapper = new ObjectMapper();
        this.path = Paths.get(path);
    }

    public void getValue() {

    }

    public void setValue(String key, double value, long usage) throws IOException {
        mapper.writeValue(path.toFile(), new ValuedWord(key, value, usage));
    }

    public void getMultiplier() {

    }

    public void setMultiplier() {

    }

    public void backup() throws IOException {
        final Calendar calendar = Calendar.getInstance(); // Simplifies it so I don't have a really long line next.
        final Path backup = path.getRoot().resolve("backups").resolve("dictionary-" + calendar.getTime().toString());
        Files.createFile(backup);
        Files.copy(path, backup);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public Path getPath() {
        return path;
    }

    public static class ValuedWord {

        final String word;
        final double value;
        final long usage;

        ValuedWord(String word, double value, long usage) {
            this.word = word;
            this.value = value;
            this.usage = usage;
        }
    }
}
