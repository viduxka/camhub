import React from "react";
import Chip from '@material-ui/core/Chip';
import PropTypes from 'prop-types';

const Label = (props) => {
  return <Chip data-test="component-cap-chip" label={props.children} style={{height: "18px", borderRadius: 6,background: "#808080", color: "white" }}/>;
};

Label.propTypes = {
  children: PropTypes.string,
};

export default Label;
