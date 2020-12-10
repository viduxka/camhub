import React from "react";
import Capability from "../../Capability/Capability";

const CameraCapabilityTable = (props) => {
  if (props.capabilityArray !== undefined) {
    return (
      <table>
        <tbody>
          <tr>
            {props.capabilityArray.map((item, index) => (
              <Capability
                data-test="component-cam-detail-capabilities"
                key={index}
                name={item}
              />
            ))}
          </tr>
        </tbody>
      </table>
    );
  }
  return null;
};

export default CameraCapabilityTable;
