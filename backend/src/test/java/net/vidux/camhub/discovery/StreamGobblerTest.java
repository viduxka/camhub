package net.vidux.camhub.discovery;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StreamGobblerTest {

  @Test
  void testSingleLine() {
    String initialString = "VDX-OD-1080p-FX-30M-620140710CCWR472049297";
    InputStream stream = new ByteArrayInputStream(initialString.getBytes());

    Assertions.assertEquals(
        initialString,
        new StreamGobbler(stream).call().get(0),
        "Expected string and generated are not the same.");
  }

  @Test
  void testMoreLines() {
    String firstLine = "VDX-OD-1080p-FX-30M-620140710CCWR472049297";
    String secondLine = "44-19-b6-4d-75-f1";
    String newLine = System.getProperty("line.separator");
    String initialString = firstLine + newLine + secondLine;
    InputStream stream = new ByteArrayInputStream(initialString.getBytes());

    List<String> expectedStrings = new ArrayList<>();
    expectedStrings.add(firstLine);
    expectedStrings.add(secondLine);

    Assertions.assertEquals(
        expectedStrings,
        new StreamGobbler(stream).call(),
        "Expected strings and generated strings are not the same.");
  }

  @Test
  void testEmptyLine() {
    String firstLine = "VDX-OD-1080p-FX-30M-620140710CCWR472049297";
    String secondLine = "44-19-b6-4d-75-f1";
    String newLine = System.getProperty("line.separator");
    String initialString = firstLine + newLine + "" + newLine + secondLine;
    InputStream stream = new ByteArrayInputStream(initialString.getBytes());

    List<String> expectedStrings = new ArrayList<>();
    expectedStrings.add(firstLine);
    expectedStrings.add(secondLine);

    Assertions.assertEquals(
        expectedStrings,
        new StreamGobbler(stream).call(),
        "Expected strings and generated strings are not the same.");
  }
}
