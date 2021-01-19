package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * This factory separates the responsibility of the process starting procedure. Its purpose is to
 * enable testability to classes which use OS processes. This class is mockable unlike the {@link
 * ProcessBuilder}.
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Component
@SuppressWarnings("unused")
class ProcessFactory {

  /**
   * This process factory uses delegation strategy to {@link ProcessBuilder}. For additional
   * information please check the {@link ProcessBuilder#start()} and the {@link
   * ProcessBuilder#ProcessBuilder(String...)}
   *
   * @see ProcessBuilder
   * @param command a string array containing the program and its arguments
   * @return a new {@link Process} object for managing the subprocess
   * @throws IOException if an I/O error occurs
   * @throws RuntimeException see {@link ProcessBuilder#start()}
   */
  @NonNull
  Process create(String... command) throws IOException {
    return new ProcessBuilder(command).start();
  }

  /**
   * This process factory uses delegation strategy to {@link ProcessBuilder}. For additional
   * information please check the {@link ProcessBuilder#start()} and the {@link
   * ProcessBuilder#ProcessBuilder(List)}
   *
   * @see ProcessBuilder
   * @param command a string array containing the program and its arguments
   * @return a new {@link Process} object for managing the subprocess
   * @throws IOException if an I/O error occurs
   * @throws RuntimeException see {@link ProcessBuilder#start()}
   */
  @NonNull
  Process create(List<String> command) throws IOException {
    return new ProcessBuilder(command).start();
  }

  @Override
  public String toString() {
    return "ProcessFactory{ARMED}";
  }
}
