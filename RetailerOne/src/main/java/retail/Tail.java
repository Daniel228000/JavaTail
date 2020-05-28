package retail;

import javax.lang.model.type.TypeMirror;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

import static java.nio.file.Files.exists;
import static java.nio.file.Files.isWritable;

public class Tail {

    private int countOfChars;
    private int countOfStrings;

    public Tail(int countOfChars, int countOfStrings) {
        this.countOfChars = countOfChars;
        this.countOfStrings = countOfStrings;
    }

    private void retail(BufferedReader reader, BufferedWriter writer) throws IOException {
        if (countOfStrings != 0) {
            cutLines(reader, writer);
        } else if (countOfChars != 0) {
            cutSymbols(reader, writer);
        } else {
            countOfStrings = 10;
            cutLines(reader, writer);
        }
    }

    private void cutLines(BufferedReader reader, BufferedWriter writer) throws IOException { //retailer for strings
        Deque<String> deque = new LinkedList<>();
        String string;
        while ((string = reader.readLine()) != null) {
            if (deque.size() == countOfStrings) {
                deque.pollFirst();
            }
            deque.add(string);
        }
        while (deque.peekFirst() != null) {
            writer.write(Objects.requireNonNull(deque.pollFirst()));
            if (!deque.isEmpty()) {
                writer.newLine();
            }
        }
    }

    private void cutSymbols(BufferedReader reader, BufferedWriter writer) throws IOException { // retailer for symbols
        Deque<Character> deque = new LinkedList<>();
        int sym ;
        while ((sym = reader.read()) > -1) {
            if (deque.size() == countOfChars) {
                deque.pollFirst();
            }
            deque.add((char) sym);
        }
        while (deque.peekFirst() != null) {
            //writer.write(deque.pollFirst());
            writer.write(String.valueOf(Objects.requireNonNull(deque.pollFirst())));
        }
    }

    public void main(ArrayList<String> inputNames, String outputName) throws IOException {

     // Path path = Paths.get(outputName);
     //     if (!exists(directory)) {
     //         Files.createDirectories(path);
     //         //  throw new NoSuchFileException(directory.toString() );
     //     }
     //     if (!isWritable(directory)) {
     //         throw new AccessDeniedException(directory.toString());
     //     }
      //Path path = Paths.get(outputName);
      //  Files.createDirectories(path);
        BufferedWriter writer = outputName == null ? new BufferedWriter(new OutputStreamWriter(System.out)) :
                Files.newBufferedWriter(Paths.get(outputName));
        if (inputNames == null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String line;
                System.out.println("Name of file(s):");
                System.out.println("Write End to finish");
                while (!(line = reader.readLine()).equals("End")) {
                    writer.write(line);
                }
                //reader.close();
                retail(reader,writer);
            }

        }
        else {
            try (writer) {
                for (String string : inputNames) {
                    try (BufferedReader reader = Files.newBufferedReader(Paths.get(string))) {
                        writer.write(new File(string).getName());
                        writer.newLine();
                        retail(reader, writer);
                        writer.newLine();
                    }
                }
            }
        }
    }
}
