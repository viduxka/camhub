import React, { Component } from "react";
import CameraDetails from "../../components/CameraDetails/CameraDetails";
import Grid from "@material-ui/core/Grid";
import CameraTable from '../../components/CameraTable/CameraTable';
import SnackBar from '../../components/SnackBar/SnackBar';

const GET_CAMERAS = process.env.REACT_APP_SERVER+"/api/v1/cameras";

class CamListContainer extends Component {
  state = {
    cameras: [],
    cameraSelected : -1,
    alert:{
      open:false,
      message: "",
      backgroundColor: "",
    },
  };

  setAlert = (message, backgroundColor) => {
    this.setState({
      alert: {
        open: true, 
        message,
        backgroundColor
      },
    });
  };

  closeAlert = () => this.setState({alert: {open: false}});

  componentDidMount() {
    fetch(GET_CAMERAS)
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
    const {cameras,cameraSelected,alert}=this.state;
    const {open,message,backgroundColor}=alert;
    return (
      <Grid
        container
        justify="flex-start"
        alignItems="flex-start"
        direction="row"
      >
        <CameraTable cameras={cameras} 
          selectedCamera={cameraSelected} 
          selectCameraHandler={this.selectCameraHandler} 
          setAlert={this.setAlert}/>
        {this.getCameraDetails()}
        <SnackBar
          open={open}
          message={message}
          onCloseAlert={this.closeAlert}
          backgroundColor={backgroundColor}/>
      </Grid>      
    );
  }
}

export default CamListContainer;
