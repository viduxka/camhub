import { shallow } from "enzyme";
import { findByTestAttr } from '../../test/testUtils';
import CameraDetails from './CameraDetails';


let wrapper=null;

let testCaps=["Test cap1", "Test cap2"];

const setup = () => {
wrapper = shallow(
  <CameraDetails 
    name="Testname"
    ip="10.30.0.51"
    firmware="5.4.3"
    lastSeen="2020-11-11 13:13"
    owner="Test Owner"
    serialNumber="123456789SN"
    capabilities={testCaps}
    _configLink="http://10.30.0.51"/>);

};

test("CameraDetails is rendered without errors", () => {
  setup();
});

test("all capability labels are rendered", () => {
  const components=findByTestAttr(wrapper,"component-cam-detail-capabilities");
  components.forEach((item)=> 
    expect(testCaps).toContain(item.prop("name")));
});

test("if caps are not defined no Capability lables are displayed", () => {
  testCaps=undefined;
  setup();
  const components=findByTestAttr(wrapper,"component-cam-detail-capabilities");
  expect(components.length).toEqual(0);
});
