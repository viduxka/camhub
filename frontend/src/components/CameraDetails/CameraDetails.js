import React from "react";
import classes from "./CameraDetails.module.css";
import Capability from "../Capability/Capability";
import Button from "@material-ui/core/Button";
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  button: {
    border: "none",
    color: "#FF9900",
    backgroundColor: "#000",
    position: "absolute",
    bottom: "10px",
    textTransform: "none",
    "&:hover": {
      backgroundColor: "#FF9900",
      border: "none",
      color: "#FFF",
    },
  }
}));

const CameraDetails = (props) => {
  const columnNames = ["Name", "Camera IP", "Firmware", "Last seen", "Owner", "Serial number"];
  const classOf = useStyles();

  const camData = [
    props.name,
    props.ip,
    props.firmware,
    props.lastSeen,
    props.owner,
    props.serialNumber];

  const cameraData = columnNames.map((item,index)=>
    <tr key={index}>
      <td>{item}</td>
      <td>{camData[index]}</td>
    </tr>
  );

  let capabilityTable = null;

  if (props.capabilities !== undefined) {
    capabilityTable = (
      <table>
        <tbody>
          <tr>
            {props.capabilities.map((item, index) => (
              <Capability data-test="component-cam-detail-capabilities" key={index} name={item} />
            ))}
          </tr>
        </tbody>
      </table>
    );
  }

  return (
    <div className={classes.DetailsContainer}>
      <div className={classes.DetailsTitle}>Details</div>
      <table className={classes.DetailsProperties}>
        <tbody>
         {cameraData}
        </tbody>
      </table>
      {capabilityTable}
      <Button target="_blank" className={classOf.button} variant="outlined" color="primary" href={props._configLink}><u>Camera config</u></Button>
    </div>
  );
};

export default CameraDetails;