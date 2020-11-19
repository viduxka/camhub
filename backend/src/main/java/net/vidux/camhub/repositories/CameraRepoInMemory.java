package net.vidux.camhub.repositories;

import net.vidux.camhub.models.Camera;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("InMemoryDB")
public class CameraRepoInMemory implements CameraRepo {
//    private static CameraRepoInMemory singleInstance = null;

    private static final List<Camera> cameraDB = new ArrayList<>();

//    private CameraRepoInMemory() {
//        CameraDB = new ArrayList<>();
//    }
//
//    public static CameraRepoInMemory getInstance(){
//        if (singleInstance == null) {
//            singleInstance = new CameraRepoInMemory();
//        }
//        return singleInstance;
//    }

    @Override
    public List<Camera> getCameras() {
        return cameraDB;
    }

    @Override
    public int addCamera(Camera camera) {
        if (camera != null){
            cameraDB.add(camera);
            return 1;
        }
        return 0;
    }
}
