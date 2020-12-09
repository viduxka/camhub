package net.vidux.camhub.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class RawCameraEventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publishRawCameraEvent(RawCameraData rawCameraData) {
        eventPublisher.publishEvent(new RawCameraEvent(rawCameraData));
    }
}
