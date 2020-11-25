import React from "react";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  root: {
    color: "white",
    background: "inherit",
    border: "solid 1px white",
    borderRadius: "9px",
    fontSize: "8px",
  },
}));

export const ReserveRelease = () => {
  const classes = useStyles();
  const onClickHandler = (event) => {
    console.log("Reserved/released");
  };

  return (
    <Button
      variant="outlined"
      classes={{ root: classes.root }}
      color="default"
      size="small"
      onClick={(e) => {
        onClickHandler.bind(this, e);
      }}
    >
      Reserve
    </Button>
  );
};
