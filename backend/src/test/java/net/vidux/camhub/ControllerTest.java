package net.vidux.camhub;

import static org.assertj.core.api.Assertions.assertThat;

import net.vidux.camhub.api.CamerasController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControllerTest {

    @Autowired
    private CamerasController controller;

    @Test
    void controllerIsNotNull(){
        assertThat(controller).isNotNull();
    }
}
