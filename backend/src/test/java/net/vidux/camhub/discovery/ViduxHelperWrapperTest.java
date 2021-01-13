package net.vidux.camhub.discovery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ViduxHelperWrapperTest {

  @Mock StreamGobbler streamGobbler;

  @Mock Process process;

  @InjectMocks ViduxHelperWrapper wrapper;

  @Test
  void testWrapper() throws IOException, TimeoutException, InterruptedException {
    List<String> list = new ArrayList<>();
    list.add(
        "\tVDX-OD-1080p-FX-30M-620140710CCWR472049297\t44-19-b6-4d-75-f1\t10.30.0.225"
            + "\t255.255.255.0\t38920\t8000\t0\t0\tV5.3.0build 151102\tV5.0, build 150327"
            + "\t2020-12-10 12:44:58\t1\tVDX-OD-1080p-FX-30M-6\t10.30.0.1"
            + "\t::\t::\t0\t79\t0\t1\n");

    Mockito.when(process.waitFor(10, TimeUnit.SECONDS)).thenReturn(true);
    Mockito.when(process.waitFor()).thenReturn(0);
    Mockito.when(streamGobbler.call()).thenReturn(list);

    Assertions.assertEquals(
        list, wrapper.findHikvisionIpCameras(), "Expected and created lists are not the same.");
  }

  @Test
  void testInputOutputException() throws InterruptedException {
    Mockito.when(process.waitFor(10, TimeUnit.SECONDS)).thenReturn(true);
    Mockito.when(process.waitFor()).thenReturn(1);
    Assertions.assertThrows(
        IOException.class, wrapper::findHikvisionIpCameras, "IOException was not thrown.");
  }

  @Test
  void testTimeOutException() throws InterruptedException {
    Mockito.when(process.waitFor(10, TimeUnit.SECONDS)).thenReturn(false);
    Assertions.assertThrows(
        TimeoutException.class,
        wrapper::findHikvisionIpCameras,
        "TimeOutException was not thrown.");
  }
}
