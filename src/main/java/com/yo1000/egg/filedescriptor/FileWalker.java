package com.yo1000.egg.filedescriptor;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoichi.kikuchi on 2016/02/29.
 */
public class FileWalker {
    public List<String> scan(Path directory) throws IOException {
        List<String> items = new ArrayList<>();

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                items.add(dir.toString());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                items.add(file.toString());
                return super.visitFile(file, attrs);
            }
        });

        return items;
    }

    public static void main(String[] args) throws IOException {
        new FileWalker().scan(Paths.get("../../")).forEach(s -> System.out.println(s));
    }
}
