package nl.bioinf.demultiplexer;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;

public class BioJavaTest {
    public static void main(String[] args) {
        try {
            // Create a DNA sequence
            DNASequence dna = new DNASequence("ATCGGCTA");
            System.out.println("DNA Sequence: " + dna.getSequenceAsString());
        } catch (CompoundNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
