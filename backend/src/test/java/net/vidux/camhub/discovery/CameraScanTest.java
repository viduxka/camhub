package net.vidux.camhub.discovery;

import static org.mockito.ArgumentMatchers.anyString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CameraScanTest {

  @Mock ViduxHelperWrapper mockedViduxHelperWrapper;
  @Mock RawCameraDataFactory mockedRawCameraDataFactory;

  @InjectMocks CameraScanViduxHelper cameraScan;

  @Test
  void testCameraScanViduxHelper() throws IOException, TimeoutException {
    List<String> fakeCameras = new ArrayList<>();
    fakeCameras.add(
        "\tVDX-OD-6MP-ML-IR-WDR20180613AAWRC28752138\t94-e1-ac-d1-2a-44\t10.30.0.51"
            + "\t255.255.255.0\t141927\t8000\t0\t0\tV5.5.51build 180326\tV7.3 build 180205"
            + "\t2020-12-09 16:30:09\t1\tVDX-OD-6MP-ML-IR-WDR\t\t10.30.0.1"
            + "\t::\t::\t64\t95\t0\t1\n");
    fakeCameras.add(
        "\tVDX-PRO-BX-1080P-ABF20150513CCWR518346210\tc4-2f-90-27-9b-6a\t10.30.0.215"
            + "\t255.255.255.0\t39169\t8000\t0\t0\tV5.3.0build 151112\tV7.0 build 151019"
            + "\t2020-12-11 00:53:13\t1\tVDX-PRO-BX-1080P-ABF\t10.30.0.1"
            + "\t::\t::\t0\t79\t0\t1\n");
    fakeCameras.add(
        "\tVDX-OD-1080p-FX-30M-620140710CCWR472049297\t44-19-b6-4d-75-f1\t10.30.0.225"
            + "\t255.255.255.0\t38920\t8000\t0\t0\tV5.3.0build 151102\tV5.0, build 150327"
            + "\t2020-12-10 12:44:58\t1\tVDX-OD-1080p-FX-30M-6\t10.30.0.1"
            + "\t::\t::\t0\t79\t0\t1\n");

    Set<RawCameraData> expectedSet = new HashSet<>();
    expectedSet.add(
        new RawCameraData("VDX-OD-6MP-ML-IR-WDR", "94-e1-ac-d1-2a-44", "10.30.0.51", "C28752138"));
    expectedSet.add(
        new RawCameraData("VDX-PRO-BX-1080P-ABF", "c4-2f-90-27-9b-6a", "10.30.0.215", "518346210"));
    expectedSet.add(
        new RawCameraData(
            "VDX-OD-1080p-FX-30M-6", "44-19-b6-4d-75-f1", "10.30.0.225", "472049297"));

    Mockito.when(mockedViduxHelperWrapper.findHikvisionIpCameras()).thenReturn(fakeCameras);
    Mockito.when(mockedRawCameraDataFactory.createRawCameraData(anyString())).thenCallRealMethod();
    Mockito.when(mockedRawCameraDataFactory.extractSerialNumber(anyString())).thenCallRealMethod();

    Set<RawCameraData> set = cameraScan.discover().getNow(new HashSet<>());

    Assertions.assertEquals(expectedSet, set);
  }
}
