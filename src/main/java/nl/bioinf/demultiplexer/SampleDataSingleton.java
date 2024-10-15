package nl.bioinf.demultiplexer;

import java.util.Map;

public class SampleDataSingleton{
    private static SampleDataSingleton instance;

    private static Object lock = new Object();
    private Map<String, SampleIndexes> sampleIndexes;

    public static SampleDataSingleton getInstance(){
        SampleDataSingleton sampleDataSingleton = instance;
        if(instance == null){
            synchronized (lock){
                sampleDataSingleton = instance;
                if(instance == null){
                    instance  = sampleDataSingleton = new SampleDataSingleton();
                }
            }
        }
        return instance;
    }

    public void setSampleIndexes(Map<String, SampleIndexes> sampleIndexes) {
        this.sampleIndexes = sampleIndexes;
    }

    public Map<String, SampleIndexes> getSampleIndexes() {
        return sampleIndexes;
    }
}
