package net.vidux.camhub.discovery;

import org.springframework.stereotype.Component;

@Component
class RawCameraDataFactory {
  private static final String REGEX = "\\t";

  boolean isNotDigitChar(char character) {
    return character > '9' || character < '0';
  }

  String extractSerialNumber(String productNumber) {
    if (productNumber == null) {
      return null;
    }
    if (productNumber.length() < 9) {
      return null;
    }

    int endIndex = productNumber.length() - 1;
    final char[] chars = productNumber.toCharArray();
    while (isNotDigitChar(chars[endIndex])) {
      endIndex--;
    }
    return productNumber.substring(endIndex - 8, endIndex + 1);
  }

  RawCameraData createRawCameraData(String rawCameraLine) {
    if (rawCameraLine == null) {
      return null;
    }
    String[] cameraInfo = rawCameraLine.split(REGEX);
    if (cameraInfo.length < 14) {
      return null;
    }
    String serialNumber = extractSerialNumber(cameraInfo[1]);
    String firmware = cameraInfo[2];
    String ip = cameraInfo[3];
    String name = cameraInfo[13];
    return RawCameraData.builder()
        .name(name)
        .firmware(firmware)
        .ipAddress(ip)
        .serialNumber(serialNumber)
        .build();
  }
}
