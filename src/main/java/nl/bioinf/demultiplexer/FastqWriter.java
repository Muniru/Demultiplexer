package nl.bioinf.demultiplexer;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastqWriter {

    public static void write(Map<String, List<FastQRead>> data, String outputDir, String subDirName) {
        // Maak directory op basis
        String dir = createDir(outputDir, subDirName);

        // Maak een file voor elke key en in elke key de data ervan
        for (Map.Entry<String, List<FastQRead>> entry : data.entrySet()) {
            String filePath = Paths.get(dir, entry.getKey()).toString();
            writeFastq(filePath, entry.getValue());
        }
    }

    private static void writeFastq(String filePath, List<FastQRead> reads) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (FastQRead read : reads) {
                // Afschrijven van de reads
                writer.write(read.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createDir (String outputDir, String dirName ) {
        Path dirPath = Paths.get(outputDir, dirName);
        try {
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory: " + dirPath, e);
        }
        return dirPath.toString();
    }

    public static void concat(String outputDir, String[] fileNames, String outputResultDir) {
        Path dirPath = Paths.get(outputDir);
        Map<String, BufferedWriter> writers = new HashMap<>();

        try {
            // Output dir
            Files.createDirectories(Paths.get(outputResultDir));

            // Alle file/filewriters initializeren voor gebruik
            for (String fileName : fileNames) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(outputResultDir, fileName).toString(), true));
                writers.put(fileName, writer);
            }

            // "Lopen" door alle directories
            Files.walk(dirPath)
                    .filter(Files::isDirectory)
                    .forEach(subDir -> {
                        for (String fileName : fileNames) {
                            // Voor elke fileName, find, lees en afscrijven.
                            Path fileToConcat = subDir.resolve(fileName);
                            if (Files.exists(fileToConcat) && Files.isRegularFile(fileToConcat)) {
                                try (BufferedReader reader = new BufferedReader(new FileReader(fileToConcat.toString()))) {
                                    String line;
                                    BufferedWriter writer = writers.get(fileName);
                                    while ((line = reader.readLine()) != null) {
                                        writer.write(line);
                                        writer.newLine();
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException("Error reading file: " + fileToConcat, e);
                                }
                            }
                        }
                    });

            // Sluit de writers
            for (BufferedWriter writer : writers.values()) {
                writer.close();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error concatenating files", e);
        }

    }

    public static void removeFilesAndDirectories(String directoryPath) throws IOException {
        Path directory = Paths.get(directoryPath);

        // Loopt langs alle bestanden en directories om ze te verwijderen
        Files.walkFileTree(directory, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        // Delete file
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        // Delete directory
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
    }
}
