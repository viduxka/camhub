import React from "react";
import classes from "./CameraDetails.module.css";
import Button from "@material-ui/core/Button";
import { makeStyles } from '@material-ui/core/styles';
import CameraCapabilityTable from './CameraCapabilityTable/CameraCapabilityTable';
import CameraDetailsTable from "./CameraDetailsTable/CameraDetailsTable";

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
  const columnNames = ["Camera name:", "Password:","Camera IP:", "Camera S/N:", "Last seen:"];
  const {name, password, ip, serialNumber, lastSeen, capabilities, _configLink} = props;
  const classOf = useStyles();

  const camData = [
    name,
    password,
    ip,
    serialNumber,    
    lastSeen,
    ];

  return (
    <div className={classes.DetailsContainer}>
      <div className={classes.DetailsTitle}>Details</div>
      <CameraDetailsTable columnNames={columnNames} camData={camData}/>
      <CameraCapabilityTable capabilityArray={capabilities}/>
      <Button target="_blank" className={classOf.button} variant="outlined" color="primary" href={_configLink}><u>Camera config</u></Button>
    </div>
  );
};

export default CameraDetails;