import React from "react";
import Tooltip from "@material-ui/core/Tooltip";
import { truncateNameIfLongerThan } from "../../helpers/helpers";
import PropTypes from 'prop-types';

const ToolTipWrapper = (props) => {
  const { name, maxLength } = props;
  let camName = <td data-test="component-camlistitem-name">{name}</td>;
  if (name.length > maxLength) {
    camName = (
      <Tooltip enterDelay={300} leaveDelay={100} arrow title={name}>
        <td data-test="component-camlistitem-name">
          {truncateNameIfLongerThan(name, maxLength)}
        </td>
      </Tooltip>
    );
  }
  return camName;
};

ToolTipWrapper.propTypes = {
  name: PropTypes.string,
  maxLength: PropTypes.number,
};

export default ToolTipWrapper;
