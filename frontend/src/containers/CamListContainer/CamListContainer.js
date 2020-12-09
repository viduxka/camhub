import React, { Component } from "react";
import classes from "./CamListContainer.module.css";
import Discover from "../../components/Discover/Discover";
import CamListItem from "../../components/CamListItem/CamListItem";
import CameraDetails from "../../components/CameraDetails/CameraDetails";
import Grid from "@material-ui/core/Grid";

const BASE_URL = "http://localhost:8080/api/v1/cameras";
const TABLE_FIELDS = [
  "Camera name",
  "Camera IP",
  "Firmware",
  "Last seen",
  "Owner",
  "Serial number",
  "Capabilities",
];

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
      cameraSelected: this.state.cameraSelected === index? -1 : index,
    });
  };

  renderCameraListTableHeader() {
    return (
      <thead>
        <tr style={{ lineHeight: "24px" }}>
          <th
            data-test="component-camlistcontainer-th"
            className={classes.CameraTableHeader}>
            <Discover />
          </th>
          {TABLE_FIELDS.map((key, index) => (
            <th
              data-test="component-camlistcontainer-th"
              className={classes.CameraTableHeader}
              key={index}>
              {key}
            </th>
          ))}
        </tr>
      </thead>
    );
  }

  getCameraDetails() {
    const selectedCamera = this.state.cameraSelected;
    return (
      <Grid item lg={3} xs={12} md={6}>
        <CameraDetails
          idx={this.state.cameraSelected}
          name={this.state.cameras[selectedCamera].name}
          ip={this.state.cameras[selectedCamera].ip}
          firmware={this.state.cameras[selectedCamera].firmware}
          lastSeen={this.state.cameras[selectedCamera].lastSeen}
          password={this.state.cameras[selectedCamera].password}
          owner={this.state.cameras[selectedCamera].owner}
          serialNumber={this.state.cameras[selectedCamera].serialNumber}
          capabilities={this.state.cameras[selectedCamera].capabilities}
          _configLink={this.state.cameras[selectedCamera]._links.camConfig.href}
        />  
      </Grid>
      
    );
  }

  renderCameraListTable() {
    let list = null;
    if (this.state.cameras !== undefined) {
      list = this.state.cameras.map((camera, index) => {
        const {name, ip, firmware, lastSeen, owner, capabilities, serialNumber } = camera;
        return (
          <CamListItem
            data-test="component-camlistcontainer-camlistitem"
            selected={this.state.cameraSelected === index ? true : false}
            key={index}
            idx={index}
            name={name}
            ip={ip}
            firmware={firmware}
            lastSeen={lastSeen}
            owner={owner}
            capabilities={capabilities}
            serialNumber={serialNumber}
            onClick={this.selectCameraHandler.bind(index)}
          />
        );
      });
    }

    return list;
  }

  render() {
    let cameraDetails = null;
    if (this.state.cameraSelected !== -1) {
      cameraDetails = this.getCameraDetails();
    }

    return (
      <Grid container justify="flex-start" alignItems="flex-start" direction="row">
        <Grid item lg={ cameraDetails === null ? 12 : 9} xs={12}>
          <table id="cameras" className={classes.CameraList}>
            {this.renderCameraListTableHeader()}
            <tbody>{this.renderCameraListTable()}</tbody>
          </table>
        </Grid>

          {cameraDetails}

      </Grid>
    );
  }
}

export default CamListContainer;
