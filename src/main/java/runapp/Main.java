package runapp;

import picocli.CommandLine;
public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new StartApp()).execute(args);
    }
}
