package nl.bioinf.demultiplexer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequenceBatchThread implements Runnable {

    private final List<FastQRead> fastqReadList;
    private final int maxError;
    private final int threadId;
    private final String outputDirectory;

    public SequenceBatchThread(int threadId,int maxError,String outputDirectory, List<FastQRead> fastqReadsList ) {
        this.fastqReadList = fastqReadsList;
        this.maxError = maxError;
        this.threadId = threadId;
        this.outputDirectory = outputDirectory;
    }

    @Override
    public void run() {
        System.out.println("Starting sequence batch thread");
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }

        Map<String, List<FastQRead>> samplesMap = new HashMap<>();

        // outs checken

        // barcode checken

        // indexes checken

        // assign sample

        // write map
        FastqWriter.write(
                samplesMap,
                outputDirectory,
                Integer.toString(threadId));

    }


}
