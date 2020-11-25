import React from "react";
import Capability from "../Capability/Capability";

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

export default CapabilityTable;
