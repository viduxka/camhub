package net.vidux.camhub.discovery

import spock.lang.Specification

import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class ViduxHelperWrapperTest extends Specification {

    def "required process factory for constructor must not be null"() {
        when:
        new ViduxHelperWrapper(null)
        then:
        thrown(NullPointerException)
    }

    def "exception is thrown if timeout is reached"() {
        given: "a process which won't answer our call"
        def process = Mock(Process) {
            waitFor(_ as Long, _ as TimeUnit) >> false
        }
        def processFactory = Mock(ProcessFactory) {
            create("vidux-helper", "system", "findHikvisionIPCameras") >> process
        }
        def wrapper = new ViduxHelperWrapper(processFactory)

        when: "we request the wrapper to look for cameras"
        wrapper.findHikvisionIpCameras()

        then: "we expect a TimeoutException and a signal to destroy the process"
        thrown(TimeoutException)
        1 * process.destroy()
    }

    def "exception is thrown if the process exit with error"() {
        given: "a process which returned with error"
        def process = Mock(Process) {
            waitFor(_ as Long, _ as TimeUnit) >> true
            exitValue() >> 99
        }
        def processFactory = Mock(ProcessFactory) {
            create("vidux-helper", "system", "findHikvisionIPCameras") >> process
        }
        def wrapper = new ViduxHelperWrapper(processFactory)

        when: "we request the wrapper to look for cameras"
        wrapper.findHikvisionIpCameras()

        then: "we expect an IOException"
        thrown(IOException)
    }

    def "process works like a charm"() {
        given: "a process which is a good boy"
        def process = Mock(Process) {
            waitFor(_ as Long, _ as TimeUnit) >> true
            exitValue() >> 0
            getInputStream() >> new ByteArrayInputStream("a cool result".getBytes())
        }
        def processFactory = Mock(ProcessFactory) {
            create("vidux-helper", "system", "findHikvisionIPCameras") >> process
        }
        def wrapper = new ViduxHelperWrapper(processFactory)

        when: "we request the wrapper to look for cameras"
        def cameras = wrapper.findHikvisionIpCameras()

        then: "we expect a spectacular result"
        ["a cool result"] == cameras
    }

    def "in the case of thread interruption runtime exception is thrown"() {
        given: "a process which is a bit slow"
        def process = Mock(Process) {
            waitFor(_ as Long, _ as TimeUnit) >> { throw new InterruptedException("thread death") }
        }
        def processFactory = Mock(ProcessFactory) {
            create("vidux-helper", "system", "findHikvisionIPCameras") >> process
        }
        def wrapper = new ViduxHelperWrapper(processFactory)

        when: "we request the wrapper to look for cameras"
        wrapper.findHikvisionIpCameras()

        then: "we expect to shut down the operation immediately"
        thrown(InterruptedIOException)
        1 * process.destroy()
    }
}
