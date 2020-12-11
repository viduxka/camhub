import React from "react";
import Label from "../Label/Label";
import PropTypes from 'prop-types';

const Capability = (props) => {
  return (
    <td data-test="component-capability-td">
      <Label>{props.name}</Label>
    </td>
  );
};

Capability.propTypes = {
  name: PropTypes.string,
};

export default Capability;
