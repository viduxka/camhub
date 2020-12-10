import { shallow, mount } from "enzyme";
import CamListContainer from "./CamListContainer";
import { findByTestAttr } from "../../test/testUtils";

test("<CamListContainer> renders without error", ()=>{
  const wrapper = shallow(<CamListContainer/>);
});
