import React from "react";
import Label from "../Label/Label";

const Capability = (props) => {
  return (
    <td data-test="component-capability-td">
      <Label>{props.name}</Label>
    </td>
  );
};

export default Capability;
