import React from 'react';
import classes from "./CamListItem.module.css";
import CapabilityTable from "../CapabilityTable/CapabilityTable";
import { ReserveRelease as ReserveReleaseBtn } from "../ReserveRelease/ReserveRelease";
import Tooltip from '@material-ui/core/Tooltip';

const CamListItem = (props) => {
  const rowColor = props.selected ? classes.Selected: classes.Normal;
  
  const truncateNameIfLonger = name => {
    if(name.length>15){
     return name.slice(0,15)+"..."; 
    }
    return name;    
  };

  const genDisplayedName = () => {
    let camName = (
      <td data-test="component-camlistitem-name">
          {props.name}
        </td>
    );
    if(props.name.length>15){
      camName = (
        <Tooltip enterDelay={300} leaveDelay={100} arrow title={props.name}>
        <td data-test="component-camlistitem-name">
          {truncateNameIfLonger(props.name)}
        </td>
      </Tooltip>
      );
     }
     return camName;
  };

  return (
    <tr 
      className={`${classes.CameraRow} ${rowColor}`}
      onClick={() => props.onClick(props.idx)}>
      <td data-test="component-camlistitem-resBtn" >
        <ReserveReleaseBtn>Reserve</ReserveReleaseBtn>
      </td>
      {genDisplayedName(props.name)}
      <td data-test="component-camlistitem-ip">{props.ip}</td>
      <td data-test="component-camlistitem-firmware">{props.firmware}</td>
      <td data-test="component-camlistitem-lastSeen">{props.lastSeen}</td>
      <td data-test="component-camlistitem-owner" >{props.owner}</td>
      <td data-test="component-camlistitem-serialNumber" >{props.serialNumber}</td>
      <td data-test="component-camlistitem-captable" className={classes.CamTableDataLong}>
        <CapabilityTable table={props.capabilities} />
      </td>
    </tr>
  );
};

export default CamListItem;