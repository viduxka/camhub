import React from 'react';
import classes from "./CamListItem.module.css";
import CapabilityTable from "../../CapabilityTable/CapabilityTable";
import { ReserveRelease as ReserveReleaseBtn } from "../../ReserveRelease/ReserveRelease";
import TableToolTipWrapper from "../../TableTooltipWrapper/TableToolTipWrapper";

const CamListItem = (props) => {
  const {selected, idx, name, ip, firmware, lastSeen, owner, capabilities, onClick} = props;
  const rowColor = selected ? classes.Selected: classes.Normal;

  return (
    <tr
      className={`${classes.CameraRow} ${rowColor}`}
      onClick={() => onClick(idx)}>
      <td data-test="component-camlistitem-resBtn" >
        <ReserveReleaseBtn>Reserve</ReserveReleaseBtn>
      </td>
        <TableToolTipWrapper data-test="component-camlistitem-tt-name"name={name} maxLength={15} />
      <td data-test="component-camlistitem-ip">{ip}</td>
      <td data-test="component-camlistitem-firmware">{firmware}</td>
      <td data-test="component-camlistitem-lastSeen">{lastSeen}</td>
      <td data-test="component-camlistitem-owner" >{owner}</td>
      <td data-test="component-camlistitem-captable" className={classes.CamTableDataLong}>
        <CapabilityTable table={capabilities} />
      </td>
    </tr>
  );
};

export default CamListItem;