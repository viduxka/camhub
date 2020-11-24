import React from "react";
import classes from "./CamListItem.module.css";
import CapabilityTable from "../CapabilityTable/CapabilityTable";
import { ReserveRelease as ReserveReleaseBtn } from "../ReserveRelease/ReserveRelease";

export const CamListItem = (props) => {
  const onclickHandler = (name) => {
    console.log(
      "[CamListItem.js] - onclickHandler(): " + name + " was clicked"
    );
  };
  return (
    <tr
      className={classes.CameraRow}
      onClick={onclickHandler.bind(this, props.name)}
    >
      <td className={classes.CamTableDataNormal}>
        <ReserveReleaseBtn>Reserve</ReserveReleaseBtn>
      </td>
      <td className={classes.CamTableDataNormal}>{props.name}</td>
      <td className={classes.CamTableDataNormal}>{props.ip}</td>
      <td className={classes.CamTableDataNormal}>{props.firmware}</td>
      <td className={classes.CamTableDataNormal}>{props.lastSeen}</td>
      <td className={classes.CamTableDataNormal}>{props.owner}</td>
      <td className={classes.CamTableDataLong}>
        <CapabilityTable table={props.capabilities} />
      </td>
    </tr>
  );
};
