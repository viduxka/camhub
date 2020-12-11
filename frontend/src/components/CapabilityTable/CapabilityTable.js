import React from "react";
import Capability from "../Capability/Capability";
import PropTypes from 'prop-types';

const CapabilityTable = (props) => {
  let capTable = null;

  if (props.table !== undefined) {
    capTable = props.table.map((cap, index) => (
      <Capability key={index} name={cap} />
    ));
  }

  return (
    <table>
      <tbody>
        <tr>{capTable}</tr>
      </tbody>
    </table>
  );
};

CapabilityTable.propTypes = {
  table: PropTypes.arrayOf(PropTypes.string),
};

export default CapabilityTable;
