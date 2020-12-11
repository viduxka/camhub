package net.vidux.camhub.discovery;

public class DiscoveryException extends Exception {

  public DiscoveryException(String errorMessage) {
    super(errorMessage);
  }

  public DiscoveryException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
