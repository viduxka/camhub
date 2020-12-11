import React, { Component } from "react";
import CameraDetails from "../../components/CameraDetails/CameraDetails";
import Grid from "@material-ui/core/Grid";
import CameraTable from '../../components/CameraTable/CameraTable';

const BASE_URL = process.env.REACT_APP_SERVER+"/api/v1/cameras";

class CamListContainer extends Component {
  state = {
    cameras: [],
    cameraSelected: -1,
  };

  componentDidMount() {
    fetch(BASE_URL)
      .then((response) => response.json())
      .then((json) => {
        this.setState({ cameras: json._embedded.cameras });
      })
      .catch(() => alert("Connection to server lost!"));
  }

  shouldComponentUpdate(nextProps, nextState) {
    return true;
  }

  selectCameraHandler = (index) => {
    this.setState({
      cameraSelected: this.state.cameraSelected === index ? -1 : index,
    });
  };

  getCameraDetails () {
    const selectedCamera = this.state.cameraSelected;
    const cameras = this.state.cameras;
    if (selectedCamera!== -1){
      return (
        <Grid item lg={3} xs={12} md={6}>
          <CameraDetails
            idx={selectedCamera}
            name={cameras[selectedCamera].name}
            ip={cameras[selectedCamera].ip}
            lastSeen={cameras[selectedCamera].lastSeen}
            password={cameras[selectedCamera].password}
            serialNumber={cameras[selectedCamera].serialNumber}
            capabilities={cameras[selectedCamera].capabilities}
            _configLink={cameras[selectedCamera]._links.camConfig.href}
          />
        </Grid>
      );
    }
    return null;    
  }  

  render() {
    return (
      <Grid
        container
        justify="flex-start"
        alignItems="flex-start"
        direction="row"
      >
        <CameraTable cameras={this.state.cameras} selectedCamera={this.state.cameraSelected} selectCameraHandler={this.selectCameraHandler}/>
        {this.getCameraDetails()}
      </Grid>
    );
  }
}

export default CamListContainer;
