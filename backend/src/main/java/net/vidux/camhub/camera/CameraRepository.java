package net.vidux.camhub.camera;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CameraRepository extends JpaRepository<Camera, String> {
  Optional<Camera> findBySerialNumber(String serialNumber);
}
