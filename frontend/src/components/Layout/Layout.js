import React, { Fragment } from "react";
import classes from "./Layout.module.css";

const layout = props => (
  <Fragment>
    <div
      style={{
        backgroundColor: "red",
        color: "white",
        height: "20px",
        textAlign: "center",
        width: "100%",
      }}
    >
      Menu, Logo, Search
    </div>
    <main className={classes.Content}>{props.children}</main>
  </Fragment>
);

export default layout;
