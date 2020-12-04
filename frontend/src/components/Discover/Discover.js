import React from "react";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";

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

const Discover = () => {
  const classes = useStyles();

  return (
    <Button
      variant="contained"
      classes={{ root: classes.root }}
      color="primary"
      size="small"
    >
      Discover
    </Button>
  );
};

export default Discover;