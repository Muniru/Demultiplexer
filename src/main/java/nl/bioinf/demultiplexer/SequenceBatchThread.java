package nl.bioinf.demultiplexer;


import java.util.List;

public class SequenceBatchThread implements Runnable {

    private List<char[][]> fastqReadList;
    private int maxError;

    public SequenceBatchThread(List<char[][]> fastqReadsList, int maxError) {
        this.fastqReadList = fastqReadsList;
        this.maxError = maxError;
    }

    @Override
    public void run() {
        System.out.println("Starting sequence batch thread");
    }
}
