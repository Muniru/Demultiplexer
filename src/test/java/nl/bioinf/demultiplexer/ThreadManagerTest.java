package nl.bioinf.demultiplexer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadManagerTest {

    @Test
    void createThread() {
        ThreadManager manager = new ThreadManager(2);
        ArrayList<FastQRead> data = new ArrayList<>();
        data.add(new FastQRead(null,null,null,null));
        data.add(new FastQRead(null,null,null,null));
        data.add(new FastQRead(null,null,null,null));
        manager.createThread(new ArrayList<>(data), 0);
        manager.createThread(new ArrayList<>(data), 0);
        manager.createThread(new ArrayList<>(data), 0);
        manager.createThread(new ArrayList<>(data), 0);
        manager.createThread(new ArrayList<>(data), 0);
        manager.createThread(new ArrayList<>(data), 0);
        manager.createThread(new ArrayList<>(data), 0);
        manager.createThread(new ArrayList<>(data), 0);
    }
}