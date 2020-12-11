package net.vidux.camhub.discovery;

class RawCameraDataFactory {
  private static final String regex = "\\t";

  String extractSerialNumber(String productNumber) {
    int endIndex = productNumber.length() - 1;
    final char[] chars = productNumber.toCharArray();
    while (chars[endIndex] > '9' || chars[endIndex] < '0') {
      endIndex--;
    }
    return productNumber.substring(endIndex - 8, endIndex + 1);
  }

  RawCameraData createRawCameraData(String rawCameraLine) {
    String[] cameraInfo = rawCameraLine.split(regex);
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
