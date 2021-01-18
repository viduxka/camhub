package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
class ProcessFactory {

  Process create(String... command) throws IOException {
    return new ProcessBuilder(command).start();
  }

  Process create(List<String> command) throws IOException {
    return new ProcessBuilder(command).start();
  }
}
