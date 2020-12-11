package net.vidux.camhub.discovery;

public class DiscoveryException extends Exception {

  public DiscoveryException() {}

  public DiscoveryException(String errorMessage) {
    super(errorMessage);
  }

  public DiscoveryException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  public DiscoveryException(Throwable cause) {
    super(cause);
  }

  public DiscoveryException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
