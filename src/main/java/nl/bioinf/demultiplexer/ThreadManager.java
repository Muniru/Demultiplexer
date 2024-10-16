package nl.bioinf.demultiplexer;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {

    private List<Thread> threads = new ArrayList<>();
    private int maxThreads;
    private int currentThread;
    public ThreadManager(int maxThreads) {
        // If invalid threads grab maximum availeble
        this.maxThreads = (maxThreads > 0) ? maxThreads : Runtime.getRuntime().availableProcessors();
        System.out.print(this.maxThreads + " threads available");
    }

    public synchronized void createThread(List<FastQRead> fastqReadList, int maxError, String outputDirectory){

        // Wait until if the max threads is reached
        while (threads.size() >= maxThreads) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Runnable runnable = new SequenceBatchThread(currentThread++, maxError,outputDirectory, fastqReadList) {
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
