package net.vidux.camhub.discovery;

import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
class RawCameraDataFactory {
  private static final String RAW_CAMERA_LINE_SEPARATOR = "\\t";

  boolean isDigit(char character) {
    return character <= '9' && character >= '0';
  }

  String extractSerialNumber(@NonNull String productNumber) {
    if (productNumber.length() < 9) {
      return null;
    }

    int endIndex = productNumber.length() - 1;
    final char[] chars = productNumber.toCharArray();
    while (!isDigit(chars[endIndex])) {
      endIndex--;
    }
    if (endIndex < 9) {
      return null;
    }
    return productNumber.substring(endIndex - 8, endIndex + 1);
  }

  RawCameraData createRawCameraData(@NonNull String rawCameraLine) {
    String[] cameraInfo = rawCameraLine.split(RAW_CAMERA_LINE_SEPARATOR);
    if (cameraInfo.length < 14) {
      return null;
    }
    return RawCameraData.builder()
        .name(cameraInfo[13])
        .firmware(cameraInfo[2])
        .ipAddress(cameraInfo[3])
        .serialNumber(extractSerialNumber(cameraInfo[1]))
        .build();
  }
}
