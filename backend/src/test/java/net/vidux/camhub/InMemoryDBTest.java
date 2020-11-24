package net.vidux.camhub;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import net.vidux.camhub.models.Camera;
import net.vidux.camhub.repositories.CameraRepoInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryDBTest {

    private CameraRepoInMemory cameraRepoInMemory;

    @BeforeEach
    void setUpDB(){
        this.cameraRepoInMemory = new CameraRepoInMemory();
    }

    @Test
    void addCameraTest(){
        Camera camera = new Camera("Alma", "123.123.123.123", "1.0", new Date(1605776141000L));
        assertEquals(cameraRepoInMemory.getCameras(),new ArrayList<>());
        assertEquals(cameraRepoInMemory.addCamera(null),0);
        assertEquals(cameraRepoInMemory.addCamera(camera),1);
        assertEquals(cameraRepoInMemory.getCameras().get(0),camera);
    }

}
