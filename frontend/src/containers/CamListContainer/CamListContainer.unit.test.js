import { shallow } from "enzyme";
import CamListContainer from "./CamListContainer";

test("<CamListContainer> renders without error", ()=>{
  const wrapper = shallow(<CamListContainer/>);
});
