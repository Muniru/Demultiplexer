package nl.bioinf.demultiplexer;

public record FastQRead(char[] identifier, char[] sequence, char[] separator, char[] quality) {}

