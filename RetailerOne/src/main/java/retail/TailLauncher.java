package retail;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.*;
import java.util.ArrayList;

public class TailLauncher {
    @Option(name = "-c", metaVar = "Chars", usage = "Set number of last chars")
    private int countOfChars;

    @Option(name = "-n", metaVar = "Strings", usage = "Set number of last strings")
    private int countOfStrings;

    @Option(name = "-o", metaVar = "OutputName", usage = "Set default name of output file")
    private String outputFileName;

    @Argument(metaVar = "InputName", usage = "input file name")
    private ArrayList<String> inputFileNames;
    private int count;
    private boolean flag;


    public static void main(String[] args) {
        new TailLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("error! java -jar Tail.jar -o OutputName " +
                    "[-c Chars || -n Strings] InputName1 InputName2 ...");
            parser.printUsage(System.err);
            System.exit(99);
        }

        if (countOfChars != 0 && countOfStrings != 0) {
            System.exit(999);
        }

        if (countOfChars != 0) {
            count = countOfChars;
            flag = true;
        } else
        if (countOfStrings != 0) {
            count = countOfStrings;
            flag = false;
        }
        else {
            count = 10;
            flag = false;
        }
        Tail tail = new Tail(count, flag);
        try {
            tail.main(inputFileNames, outputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(9999);
        }
    }
}
