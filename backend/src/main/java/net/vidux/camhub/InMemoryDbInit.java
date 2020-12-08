package net.vidux.camhub;

import net.vidux.camhub.camera.Camera;
import net.vidux.camhub.camera.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Profile("!test")
public class InMemoryDbInit implements ApplicationRunner {

  @Autowired private CameraRepository db;

  @Override
  public void run(ApplicationArguments args) {
    db.save(
        Camera.builder()
            .name("Alma")
            .ip("123.123.123.123")
            .firmware("1.0")
            .lastSeen(Instant.ofEpochMilli(1605776141000L))
            .password("asd")
            .serialNumber("1234")
            .build());
    db.save(
        Camera.builder()
            .name("Barack")
            .ip("123.123.123.124")
            .firmware("1.0")
            .lastSeen(Instant.ofEpochMilli(1605776151000L))
            .password("asd2")
            .serialNumber("6789")
            .build());
    db.save(
        Camera.builder()
            .name("Alma")
            .ip("123.123.123.125")
            .firmware("1.0")
            .lastSeen(Instant.ofEpochMilli(1605776161000L))
            .password("asd3")
            .serialNumber("1289")
            .build());
  }
}
