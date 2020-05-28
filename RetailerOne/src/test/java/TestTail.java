
import org.junit.Test;
import retail.TailLauncher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

public class TestTail {

    private String[] args1 = {"-o", "test\\output.txt", "test\\input1.txt"}; // default 10 lines
    private String[] args2 = {"-n", "3", "-o", "test\\output.txt",           // 3 lines
            "test\\input1.txt", "test\\input2.txt"};
    private String[] args3 = {"-c", "19", "-o", "test\\output.txt",          // 19 symbols
            "test\\input1.txt", "test\\input2.txt"};
    private String[] args4 = {"-c", "3", "-o", "test\\output.txt",           // 3 symbols
            "test\\input1.txt"};
    private String[] args5 = {"-n", "512", "-o", "test\\output.txt",         // countOfStrings > FileStringSize
            "test\\input2.txt"};
    private String[] args6 = {"-n", "10", "-c", "32", "test\\input1.txt"};   // both flags activated
   // private String[] args7 = {"-c", "7", "test\\unknown.txt"};               // unknown file
   // private String[] args8 = {"-o", "test\\output.txt"};                     // no input file, write "end" after writing files


    @Test
    public void test1() throws IOException {
        TailLauncher.main(args1);
        assertEquals(Files.readAllLines(Paths.get("test\\output.txt")),
                Files.readAllLines(Paths.get("test\\expected1.txt")));
    }


    @Test
    public void test2() throws IOException {
        TailLauncher.main(args2);
        assertEquals(Files.readAllLines(Paths.get("test\\output.txt")),
                Files.readAllLines(Paths.get("test\\expected2.txt")));
    }

    @Test
    public void test3() throws IOException {
        TailLauncher.main(args3);
        assertEquals(Files.readAllLines(Paths.get("test\\output.txt")),
                Files.readAllLines(Paths.get("test\\expected3.txt")));
    }

    @Test
    public void test4() throws IOException {
        TailLauncher.main(args4);
        assertEquals(Files.readAllLines(Paths.get("test\\output.txt")),
                Files.readAllLines(Paths.get("test\\expected4.txt")));
    }

    @Test
    public void test5() throws IOException {
        TailLauncher.main(args5);
        assertEquals(Files.readAllLines(Paths.get("test\\output.txt")),
                Files.readAllLines(Paths.get("test\\expected5.txt")));
    }

    @Test
    public void testErrorOne () {
        assertThrows(Error.class, () -> TailLauncher.main(args6));
    }

    // @Test
    // public void testErrorTwo () {
    //     assertThrows(Error.class, () -> TailLauncher.main(args7));
    // }


}