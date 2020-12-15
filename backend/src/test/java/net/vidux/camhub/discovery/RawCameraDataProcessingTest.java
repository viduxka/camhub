package net.vidux.camhub.discovery;

import net.vidux.camhub.camera.Camera;
import net.vidux.camhub.camera.CameraRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RawCameraDataProcessingTest {

  @Mock
  CameraRepository mockCameraRepository;

  @Mock
  CameraFactory fakeCameraFactory;

  @InjectMocks
  RawCameraDataProcessing rawCameraDataProcessing;

  @Test
  void processRawCameraDataTestNewCamera(){
    RawCameraData fakeRawCameraData = mock(RawCameraData.class);
    when(fakeRawCameraData.getSerialNumber())
            .thenAnswer(
                    invocation -> {
                      return "1";
                    });
    when(mockCameraRepository.findBySerialNumber("1"))
            .thenAnswer(
                    invocation -> {
                      return null;
                    });
    Instant timeStamp = Instant.now();
    Camera fakeCam = mock(Camera.class);
    when(fakeCameraFactory.createCameraFromRawCameraDataAndTimeStamp(fakeRawCameraData, timeStamp))
            .thenAnswer(
                    invocation -> {
                      return fakeCam;
                    });

    rawCameraDataProcessing.processRawCameraData(fakeRawCameraData, timeStamp);

    verify(fakeCameraFactory, times(1)).createCameraFromRawCameraDataAndTimeStamp(fakeRawCameraData, timeStamp);
    verify(mockCameraRepository, times(1)).save(fakeCam);
  }

  @Test
  void processRawCameraDataTestUpdateCamera(){
    RawCameraData fakeRawCameraData = mock(RawCameraData.class);
    when(fakeRawCameraData.getSerialNumber())
            .thenAnswer(
                    invocation -> {
                      return "1";
                    });
    Camera fakeCam = mock(Camera.class);
    when(mockCameraRepository.findBySerialNumber("1"))
            .thenAnswer(
                    invocation -> {
                      return fakeCam;
                    });
    Instant timeStamp = Instant.now();

    rawCameraDataProcessing.processRawCameraData(fakeRawCameraData, timeStamp);

    verify(fakeCam, times(1)).setLastSeen(timeStamp);
    verify(mockCameraRepository, times(1)).save(fakeCam);

  }
}