import React from "react";
import classes from "./CamListItem.module.css";
import CapabilityTable from "../CapabilityTable/CapabilityTable";
import { ReserveRelease as ReserveReleaseBtn } from "../ReserveRelease/ReserveRelease";

const CamListItem = (props) => {
  const onclickHandler = (name) => {
  //TODO
  };

  return (
    <tr
      className={classes.CameraRow}
      onClick={onclickHandler.bind(this, props.name)}
    >
      <td data-test="component-camlistitem-resBtn" className={classes.CamTableDataNormal}>
        <ReserveReleaseBtn>Reserve</ReserveReleaseBtn>
      </td>
      <td data-test="component-camlistitem-name" className={classes.CamTableDataNormal}>{props.name}</td>
      <td data-test="component-camlistitem-ip" className={classes.CamTableDataNormal}>{props.ip}</td>
      <td data-test="component-camlistitem-firmware" className={classes.CamTableDataNormal}>{props.firmware}</td>
      <td data-test="component-camlistitem-lastSeen" className={classes.CamTableDataNormal}>{props.lastSeen}</td>
      <td data-test="component-camlistitem-owner" className={classes.CamTableDataNormal}>{props.owner}</td>
      <td data-test="component-camlistitem-captable" className={classes.CamTableDataLong}>
        <CapabilityTable table={props.capabilities} />
      </td>
    </tr>
  );
};

export default CamListItem;