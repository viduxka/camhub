import React  from 'react';
import Snackbar from "@material-ui/core/Snackbar/Snackbar";
import PropTypes from 'prop-types';

const SnackBar = (props) => {
  const snackBarStyle = {
    color: "black",
    borderRadius: "12px",
    backgroundColor: props.backgroundColor,
    justifyContent: "center",
  };

  return (
    <Snackbar data-test="comp-snackbar"
      open={props.open}
      message={props.message}
      ContentProps={{style: snackBarStyle}}
      anchorOrigin={{vertical: "bottom", horizontal: "center"}}
      onClose={() => props.onCloseAlert()}
      autoHideDuration={3500}/>
  );
};

SnackBar.propTypes = {
  open: PropTypes.bool,
  message: PropTypes.string,
  backgroundColor: PropTypes.string,
  onCloseAlert: PropTypes.func,
};

export default SnackBar;
