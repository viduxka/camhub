import React, { Component } from "react";
import classes from "./CamListContainer.module.css";
import Discover from "../../components/Discover/Discover";
import CamListItem from "../../components/CamListItem/CamListItem";

const BASE_URL = "http://localhost:8080/api/v1/cameras";
const TABLE_FIELDS = [
  "Camera name",
  "Camera IP",
  "Firmware",
  "Last seen",
  "Owner",
  "Capabilities",
];

class CamListContainer extends Component {
  state = {
    cameras: [],
  };

  getCameras = () => {
    fetch(BASE_URL)
    .then((response) => response.json())
    .then((json) => {
      this.setState({ cameras: json._embedded.cameras });
    })
    .catch(() => alert('Connection to server lost!'));
  };

  componentDidMount() {
    this.getCameras();
  }

  shouldComponentUpdate(nextProps, nextState) {
    return true;
  }

  renderCameraListTableHeader() {
    return (
      <thead>
        <tr>
          <th data-test="component-camlistcontainer-th" className={classes.CameraTableHeader}>
            <Discover />
          </th>
          {TABLE_FIELDS.map((key, index) => (
            <th data-test="component-camlistcontainer-th" className={classes.CameraTableHeader} key={index}>
              {key}
            </th>
          ))}
        </tr>
      </thead>
    );
  }

  renderCameraListTable() {
    let list = null;
    if (this.state.cameras !== undefined) {
      list = this.state.cameras.map((camera, index) => {
        const { name, ip, firmware, lastSeen, owner, capabilities } = camera;
        return (
          <CamListItem data-test="component-camlistcontainer-camlistitem"
            key={index}
            name={name}
            ip={ip}
            firmware={firmware}
            lastSeen={lastSeen}
            owner={owner}
            capabilities={capabilities}
          />
        );
      });
    }

    return list;
  }

  render() {
    return (
      <div>
        <table id="cameras" className={classes.CameraList}>
          {this.renderCameraListTableHeader()}
          <tbody>{this.renderCameraListTable()}</tbody>
        </table>
      </div>
    );
  }
}

export default CamListContainer;
