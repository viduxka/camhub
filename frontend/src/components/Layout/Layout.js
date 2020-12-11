import React, { Fragment } from "react";
import classes from "./Layout.module.css";
import PropTypes from 'prop-types';

const Layout = (props) => (
  <Fragment>
    <main className={classes.Content}>{props.children}</main>
  </Fragment>
);

Layout.propTypes = {
  children: PropTypes.any,
};

export default Layout;
