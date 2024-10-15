package nl.bioinf.demultiplexer;


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
        SampleDataSingleton instance = SampleDataSingleton.getInstance();
        Map<String, SampleIndexes> indexesMap = instance.getSampleIndexes();
        Map<String, List<FastQRead>> samplesMap = new HashMap<>();

        System.out.println(fastqReadList.size());
        for (FastQRead fastqRead : fastqReadList) {
            System.out.println(fastqRead);
        }
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
