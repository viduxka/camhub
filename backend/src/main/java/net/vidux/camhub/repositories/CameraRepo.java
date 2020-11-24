package net.vidux.camhub.repositories;

import net.vidux.camhub.models.Camera;

import java.util.List;

public interface CameraRepo {
  List<Camera> getCameras();

  int addCamera(Camera camera);
}
