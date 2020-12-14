package net.vidux.camhub.discovery;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    List<String> list = new ArrayList<>();
    new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(list::add);
    return list;
  }
}
