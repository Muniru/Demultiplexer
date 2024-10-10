package nl.bioinf.demultiplexer;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadManagerTest {

    @Test
    void createThread() {
        ThreadManager manager = new ThreadManager(2);
        ArrayList<FastQRead> data = new ArrayList<>();
        String outputDir = Paths.get("src", "test", "output").toAbsolutePath().toString();
        data.add(new FastQRead(null, null, null, null));
        data.add(new FastQRead(null, null, null, null));
        data.add(new FastQRead(null, null, null, null));
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
    }
}