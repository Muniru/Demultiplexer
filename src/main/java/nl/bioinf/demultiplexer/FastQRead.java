package nl.bioinf.demultiplexer;

public record FastQRead(char[] identifier, char[] sequence, char[] separator, char[] quality) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(identifier);
        sb.append("\n");
        sb.append(sequence);
        sb.append("\n");
        sb.append(separator);
        sb.append("\n");
        sb.append(quality);
        return sb.toString();
    }
}

