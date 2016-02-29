package com.yo1000.egg.filedescriptor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileRecursion {
    public List<String> scan(Path directory) throws IOException {
        List<String> items = new ArrayList<>();

        Files.list(directory).forEach(path -> {
            items.add(path.toString());

            if (!path.toFile().isDirectory()) {
                return;
            }

            try {
                items.addAll(scan(path));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });

        return items;
    }

    public static void main(String[] args) throws IOException {
        new FileRecursion().scan(Paths.get("../../")).forEach(s -> System.out.println(s));
    }
}
