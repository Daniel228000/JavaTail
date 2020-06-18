package retail;


import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;


public class Tail {

    private int count;
    private boolean isByChar;

    public Tail(int count, boolean isByChar ) {
        this.count = count;
        this.isByChar = isByChar;


    }

    private void retail(BufferedReader reader, BufferedWriter writer) throws IOException {
        if (!isByChar) {
            cutLines(reader, writer);
        } else {
            cutSymbols(reader, writer);
        }
    }

    private void cutLines(BufferedReader reader, BufferedWriter writer) throws IOException { //retailer for strings
        Deque<String> deque = new LinkedList<>();
        String string;
        while ((string = reader.readLine()) != null) {
            if (deque.size() == count) {
                deque.pollFirst();
            }
            deque.add(string);
        }
        while (!deque.isEmpty()) {
            writer.write(Objects.requireNonNull(deque.pollFirst()));
                writer.newLine();
        }
    }

    private void cutSymbols(BufferedReader reader, BufferedWriter writer) throws IOException { // retailer for symbols
        Deque<Character> deque = new LinkedList<>();
        int sym ;
        while ((sym = reader.read()) > -1) {
            if (deque.size() == count) {
                deque.pollFirst();
            }
            deque.add((char) sym);
        }
        while (!deque.isEmpty()) {
            writer.write(Objects.requireNonNull(deque.pollFirst()));
        }
    }

    public void main(ArrayList<String> inputNames, String outputName) throws IOException {
        BufferedWriter writer = outputName == null ? new BufferedWriter(new OutputStreamWriter(System.out)) :
                Files.newBufferedWriter(Paths.get(outputName));
        if (inputNames == null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
               // String line;
               // System.out.println("Name of file(s):");
               // System.out.println("Write End to finish");
               // while (!(line = reader.readLine()).equals("End")) {
               //     writer.write(line);
               //     //reader.close();
               // }
                retail(reader,writer);
            }

        }
        else {
            try (writer) {
                for (String string : inputNames) {
                    try (BufferedReader reader = Files.newBufferedReader(Paths.get(string))) {
                       if (inputNames.size() > 1) writer.write(string);
                        writer.newLine();
                        retail(reader, writer);
                        writer.newLine();
                    }
                }
            }
        }
    }
}
