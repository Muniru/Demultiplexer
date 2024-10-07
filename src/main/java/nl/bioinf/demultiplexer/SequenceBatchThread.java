package nl.bioinf.demultiplexer;


import java.util.List;

public class SequenceBatchThread implements Runnable {

    private List<FastQRead> fastqReadList;
    private int maxError;

    public SequenceBatchThread(List<FastQRead> fastqReadsList, int maxError) {
        this.fastqReadList = fastqReadsList;
        this.maxError = maxError;
    }

    @Override
    public void run() {
        System.out.println("Starting sequence batch thread");
        for (int i = 0; i < 5000; i++) {
            System.out.println(i);
        }

    }
}
