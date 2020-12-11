import React from "react";
import classes from "../CameraDetails.module.css";
import PropTypes from 'prop-types';

const CameraDetailsTable = (props) => {
  const cameraData = props.columnNames.map((item, index) => (
    <tr key={index}>
      <td>{item}</td>
      <td data-test="component-cdt-td">{props.camData[index]}</td>
    </tr>
  ));

  return (
    <table className={classes.DetailsProperties}>
      <tbody>{cameraData}</tbody>
    </table>
  );
};

CameraDetailsTable.propTypes = {
  columnNames: PropTypes.arrayOf(PropTypes.string),
  camData: PropTypes.arrayOf(PropTypes.string),
};

export default CameraDetailsTable;
