import { React } from "react";
import classes from "../../containers/CamListContainer/CamListContainer.module.css";
import CamListItem from "./CamListItem/CamListItem";
import Grid from "@material-ui/core/Grid";

const CameraTable = (props) => {
  const TABLE_FIELDS = [
    "Camera name",
    "Camera IP",
    "Firmware",
    "Last seen",
    "Owner",
    "Capabilities",
  ];

  const renderCameraListTableHeader = () => {
    return (
      <thead>
        <tr style={{ lineHeight: "24px" }}>
          <th
            data-test="component-camlistcontainer-th"
            className={classes.CameraTableHeader}
          ></th>
          {TABLE_FIELDS.map((key, index) => (
            <th
              data-test="component-camlistcontainer-th"
              className={classes.CameraTableHeader}
              key={index}
            >
              {key}
            </th>
          ))}
        </tr>
      </thead>
    );
  };

  const renderCameraListTable = () => {
    if (props.cameras !== undefined) {
      return props.cameras.map((camera, index) => {
        const {name, ip, firmware, lastSeen, owner, capabilities, serialNumber } = camera;
        return (
          <CamListItem
            data-test="component-camlistcontainer-camlistitem"
            selected={props.selectedCamera === index}
            key={index}
            idx={index}
            name={name}
            ip={ip}
            firmware={firmware}
            lastSeen={lastSeen}
            owner={owner}
            capabilities={capabilities}
            onClick={props.selectCameraHandler.bind(index)}
          />
        );
      });
    }
    return null;
  };

  const renderCameraList = (extended) => {
    return (
      <Grid item lg={extended ? 12 : 9} xs={12}>
        <table id="cameras" className={classes.CameraList}>
          {renderCameraListTableHeader()}
          <tbody>{renderCameraListTable()}</tbody>
        </table>
      </Grid>
    );
  };

  return renderCameraList(props.selectedCamera === -1);
};

export default CameraTable;
