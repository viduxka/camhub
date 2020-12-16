package net.vidux.camhub.discovery;

public class CameraDiscoveryException extends Exception {

  public CameraDiscoveryException() {}

  public CameraDiscoveryException(String errorMessage) {
    super(errorMessage);
  }

  public CameraDiscoveryException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }

  public CameraDiscoveryException(Throwable cause) {
    super(cause);
  }

  public CameraDiscoveryException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
