package net.vidux.camhub.repositories;

import net.vidux.camhub.models.Camera;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("InMemoryDB")
public class CameraRepoInMemory implements CameraRepo {

  private final List<Camera> cameraDB = new ArrayList<>();

  @Override
  public List<Camera> getCameras() {
    return cameraDB;
  }

  @Override
  public int addCamera(Camera camera) {
    if (camera != null) {
      cameraDB.add(camera);
      return 1;
    }
    return 0;
  }
}
