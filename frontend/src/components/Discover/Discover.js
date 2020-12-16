import React from "react";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";
import axios from "axios";

const DISCOVER_URL=process.env.REACT_APP_SERVER+"/api/v1/discovery";

const useStyles = makeStyles((theme) => ({
  root: {
    color: "black",
    background: "#FF9900",
    border: "none",
    borderRadius: "9px",
    textAlign: "center",
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    "&:hover": {
      backgroundColor: "#FF9900",
      color: "#FFF",
    },
  },
}));

const Discover = (props) => {
  const alertColor = {
    ok: "#FF9900",
    error: "#F14F4F",
  };
  const classes = useStyles();
  const setSnackBar = props.setAlert;
  const sendDiscover = () => {
    axios.post(DISCOVER_URL, "", {timeout: 2000})
    .then(response => setSnackBar("Success! Discovery started ( Status: "+response.status+" )", alertColor.ok))
    .catch(err => setSnackBar("Error! Discovery could not start ( "+ err+" )", alertColor.error));
  }
  
  return (
    <Button data-test="comp-discover"
      variant="contained"
      classes={{ root: classes.root }}
      color="primary"
      size="small"
      onClick={() => sendDiscover()}>
      Discover
    </Button>
  );
};

export default Discover;