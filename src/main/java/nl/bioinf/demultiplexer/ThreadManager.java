package nl.bioinf.demultiplexer;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {

    private List<Thread> threads = new ArrayList<>();
    private int maxThreads;
    public ThreadManager(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public synchronized void createThread(List<FastQRead> fastqReadList, int maxError){

        // Wait until if the max threads is reached
        while (threads.size() >= maxThreads) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Runnable runnable = new SequenceBatchThread(fastqReadList, maxError) {
            @Override
            public void run() {
                try {
                    super.run();
                } finally {
                    ThreadManager.this.threadCompleted(Thread.currentThread());
                }
            }
        };

        Thread thread = new Thread(runnable);
        threads.add(thread);
        thread.start();
    }


    private synchronized void threadCompleted(Thread thread) {
        // Verwijder de thread en laat een wachtende thread weten dat ze kunnen starten
        threads.remove(thread);
        notify();
    }
}
