package net.vidux.camhub.discovery;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import lombok.NonNull;

class StreamGobbler implements Callable<List<String>> {
  private InputStream inputStream;

  public StreamGobbler(@NonNull InputStream inputStream) {
    this.inputStream = inputStream;
  }

  @Override
  public List<String> call() {
    ArrayList<String> list = new ArrayList<>();
    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        .lines()
        .filter(line -> !line.isBlank())
        .forEach(list::add);
    return list;
  }
}
