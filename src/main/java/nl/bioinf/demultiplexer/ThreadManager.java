package nl.bioinf.demultiplexer;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {

    private List<Thread> threads = new ArrayList<>();
    private int maxThreads;
    public ThreadManager(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public void createThread(List<char[][]> fastqReadList, int maxError){
        Runnable runnable = new SequenceBatchThread(fastqReadList, maxError);
        throw new UnsupportedOperationException("not implemented yet");
    }

}
