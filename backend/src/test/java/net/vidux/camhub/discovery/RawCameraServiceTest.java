package net.vidux.camhub.discovery;

import net.vidux.camhub.camera.Camera;
import net.vidux.camhub.camera.CameraRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RawCameraServiceTest {

  @Mock CameraRepository mockCameraRepository;

  @Mock CameraFactory fakeCameraFactory;

  @InjectMocks RawCameraService rawCameraService;

  @Test
  void testProcessRawCameraDataNewCamera() {
    RawCameraData fakeRawCameraData = mock(RawCameraData.class);
    when(fakeRawCameraData.getSerialNumber())
        .thenAnswer(
            invocation -> "1");
    when(mockCameraRepository.findBySerialNumber("1"))
        .thenAnswer(
            invocation -> Optional.empty());
    Instant timeStamp = Instant.now();
    Camera fakeCam = mock(Camera.class);
    when(fakeCameraFactory.createCameraFromRawCameraDataAndTimeStamp(fakeRawCameraData, timeStamp))
        .thenAnswer(
            invocation -> fakeCam);

    rawCameraService.processRawCameraData(fakeRawCameraData, timeStamp);

    verify(fakeCameraFactory, times(1))
        .createCameraFromRawCameraDataAndTimeStamp(fakeRawCameraData, timeStamp);
    verify(mockCameraRepository, times(1)).save(fakeCam);
  }

  @Test
  void testProcessRawCameraDataUpdateCamera() {
    RawCameraData fakeRawCameraData = mock(RawCameraData.class);
    when(fakeRawCameraData.getSerialNumber())
        .thenAnswer(
            invocation -> "1");
    Camera fakeCam = mock(Camera.class);
    when(mockCameraRepository.findBySerialNumber("1"))
        .thenAnswer(
            invocation -> Optional.of(fakeCam));
    Instant timeStamp = Instant.now();

    rawCameraService.processRawCameraData(fakeRawCameraData, timeStamp);

    verify(fakeCam, times(1)).setLastSeen(timeStamp);
    verify(mockCameraRepository, times(1)).save(fakeCam);
  }
}
