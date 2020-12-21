package net.vidux.camhub.api;

import lombok.extern.slf4j.Slf4j;
import net.vidux.camhub.discovery.CameraDiscoveryException;
import net.vidux.camhub.discovery.CameraDiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/discovery")
@RestController
@CrossOrigin
@Slf4j
class DiscoveryController {

  private final CameraDiscoveryService discoveryService;

  @Autowired
  DiscoveryController(CameraDiscoveryService discoveryService) {
    this.discoveryService = discoveryService;
  }

  @PostMapping
  public void requestDiscoveryStart() {
    try {
      discoveryService.requestDiscovery();
    } catch (CameraDiscoveryException e) {
      log.warn("Discovery Exception caught when trying to start a new discovery", e);
    }
  }
}
