import React from "react";
import classes from "./Label.module.css";

const Label = (props) => {
  return <p className={classes.roundedLabel}>{props.children}</p>;
};

export default Label;
