package net.vidux.camhub.discovery;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CameraScanTest {

  @Mock ViduxHelperWrapper mockedViduxHelperWrapper;
  @Mock RawCameraDataFactory mockedRawCameraDataFactory;

  @InjectMocks CameraScanViduxHelper cameraScan;

  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Test
  void testCameraScanViduxHelper() {
    List<String> fakeCameras = new ArrayList<>();
    String camera =
        "\tVDX-OD-6MP-ML-IR-WDR20180613AAWRC28752138\t94-e1-ac-d1-2a-44\t10.30.0.51"
            + "\t255.255.255.0\t141927\t8000\t0\t0\tV5.5.51build 180326\tV7.3 build 180205"
            + "\t2020-12-09 16:30:09\t1\tVDX-OD-6MP-ML-IR-WDR\t\t10.30.0.1"
            + "\t::\t::\t64\t95\t0\t1\n";
    fakeCameras.add(camera);

    Set<RawCameraData> expectedSet = new HashSet<>();
    RawCameraData rawCameraData =
        new RawCameraData("VDX-OD-6MP-ML-IR-WDR", "94-e1-ac-d1-2a-44", "10.30.0.51", "C28752138");
    expectedSet.add(rawCameraData);
    try {
      Mockito.when(mockedViduxHelperWrapper.findHikvisionIpCameras()).thenReturn(fakeCameras);
      Mockito.when(mockedRawCameraDataFactory.createRawCameraData(anyString()))
          .thenCallRealMethod();
      Mockito.when(mockedRawCameraDataFactory.extractSerialNumber(anyString()))
          .thenCallRealMethod();
      Mockito.when(mockedRawCameraDataFactory.isDigit(anyChar())).thenCallRealMethod();
      Set<RawCameraData> set = cameraScan.scanCams().getNow(new HashSet<>());

      Assertions.assertEquals(expectedSet, set);

    } catch (IOException | TimeoutException e) {
      System.err.println(e);
    }
  }
}
