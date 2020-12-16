package net.vidux.camhub.api;

import net.vidux.camhub.discovery.CameraDiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/discovery")
@RestController
@CrossOrigin
public class DiscoveryController {

  @Autowired private CameraDiscoveryService discoveryService;

  @GetMapping
  public void disco() {
    try {
      discoveryService.requestDiscovery();
    } catch (Exception e) {
      System.err.println("e");
    }
  }
}
