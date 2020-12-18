package net.vidux.camhub.discovery;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class RawCameraDataFactory {
  private static final String RAW_CAMERA_LINE_SEPARATOR = "\\t";
  private static final int SERIAL_NUMBER_LENGTH = 9;
  private static final int CAMERA_LENGTH = 14;
  private static final int PRODUCT_NUMBER = 1;
  private static final int IP = 3;
  private static final int FIRMWARE = 2;
  private static final int NAME = 13;

  String extractSerialNumber(@NonNull String productNumber) {
    if (productNumber.length() < SERIAL_NUMBER_LENGTH) {
      log.error("Invalid product number: " + productNumber);
      return null;
    }

    int endIndex = productNumber.length() - 1;
    final char[] chars = productNumber.toCharArray();
    while (!Character.isDigit(chars[endIndex])) {
      endIndex--;
    }
    if (endIndex < SERIAL_NUMBER_LENGTH) {
      log.error("Invalid product number: " + productNumber);
      return null;
    }
    return productNumber.substring(endIndex - SERIAL_NUMBER_LENGTH + 1, endIndex + 1);
  }

  // If the input is incorrect can throw IllegalArgumentException
  RawCameraData createRawCameraData(@NonNull String rawCameraLine) {
    String[] cameraInfo = rawCameraLine.split(RAW_CAMERA_LINE_SEPARATOR);
    if (cameraInfo.length < CAMERA_LENGTH) {
      log.error("Incorrect input length: " + cameraInfo.length);
      throw new IllegalArgumentException("Not correct input length: " + cameraInfo.length);
    }
    return RawCameraData.builder()
        .name(cameraInfo[NAME])
        .firmware(cameraInfo[FIRMWARE])
        .ipAddress(cameraInfo[IP])
        .serialNumber(extractSerialNumber(cameraInfo[PRODUCT_NUMBER]))
        .build();
  }
}
