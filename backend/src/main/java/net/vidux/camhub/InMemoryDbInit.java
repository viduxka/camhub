package net.vidux.camhub;

import net.vidux.camhub.camera.Camera;
import net.vidux.camhub.camera.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Profile("!test")
public class InMemoryDbInit implements ApplicationRunner {

  @Autowired
  private CameraRepository db;

  @Override
  public void run(ApplicationArguments args) {
    db.save(new Camera("Alma", "123.123.123.123", "1.0", new Date(1605776141000L),"asd","1234"));
    db.save(new Camera("Barack", "123.123.123.124", "1.0", new Date(1605776151000L),"asd2","6789"));
    db.save(new Camera("Citrom", "123.123.123.125", "1.0", new Date(1605776161000L),"asd3","1289"));
  }
}
