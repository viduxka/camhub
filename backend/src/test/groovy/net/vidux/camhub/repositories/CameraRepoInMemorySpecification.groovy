package net.vidux.camhub.repositories

import net.vidux.camhub.models.Camera
import spock.lang.Specification

class CameraRepoInMemorySpecification extends Specification {

    private static final String TEST_NAME = "VDX-TEST"
    private static final String TEST_IP = "why can I write here anything?"
    private static final String TEST_FW = "V5"
    private static final Date TEST_DATE = new Date(2020, 11, 25)

    def "empty at creation"() {
        given:
        def repo = new CameraRepoInMemory()
        when:
        def cameras = repo.getCameras()
        then:
        cameras.isEmpty()
    }

    def "null addition do nothing but response with 0"() {
        given:
        def repo = new CameraRepoInMemory()
        when:
        def response = repo.addCamera(null)
        def cameras = repo.getCameras()
        then:
        response == 0
        cameras.isEmpty()
    }

    def "what added must be retrieved"() {
        given:
        def repo = new CameraRepoInMemory()
        def camera = Camera.builder().name(TEST_NAME)
                           .ip(TEST_IP)
                           .firmware(TEST_FW)
                           .lastSeen(TEST_DATE)
                           .build()
        when:
        def response = repo.addCamera(camera)
        def cameras = repo.getCameras()
        then:
        response == 1
        !cameras.isEmpty()
        def cameraFromRepo = cameras.get(0)
        TEST_NAME == cameraFromRepo.getName()
        TEST_IP == cameraFromRepo.getIp()
        TEST_FW == cameraFromRepo.getFirmware()
        TEST_DATE == cameraFromRepo.getLastSeen()
    }
}
