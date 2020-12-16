package net.vidux.camhub.discovery;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class RawCameraDataFactory {
  private static final String RAW_CAMERA_LINE_SEPARATOR = "\\t";

  String extractSerialNumber(@NonNull String productNumber) {
    if (productNumber.length() < 9) {
      log.error("Invalid product number: " + productNumber);
      return null;
    }

    int endIndex = productNumber.length() - 1;
    final char[] chars = productNumber.toCharArray();
    while (!Character.isDigit(chars[endIndex])) {
      endIndex--;
    }
    if (endIndex < 9) {
      log.error("Invalid product number: " + productNumber);
      return null;
    }
    return productNumber.substring(endIndex - 8, endIndex + 1);
  }

  // If the input is incorrect can throw IllegalArgumentException
  RawCameraData createRawCameraData(@NonNull String rawCameraLine) {
    String[] cameraInfo = rawCameraLine.split(RAW_CAMERA_LINE_SEPARATOR);
    if (cameraInfo.length < 14) {
      log.error("Incorrect input length: " + cameraInfo.length);
      throw new IllegalArgumentException("Not correct input length: " + cameraInfo.length);
    }
    return RawCameraData.builder()
        .name(cameraInfo[13])
        .firmware(cameraInfo[2])
        .ipAddress(cameraInfo[3])
        .serialNumber(extractSerialNumber(cameraInfo[1]))
        .build();
  }
}
