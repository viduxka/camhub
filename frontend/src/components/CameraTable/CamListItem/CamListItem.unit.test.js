import { shallow,mount } from "enzyme";
import { ReserveRelease } from "../../../components/ReserveRelease/ReserveRelease";
import CamListItem  from './CamListItem';
import { findByTestAttr } from '../../../test/testUtils';


let wrapper = null;
const testProps = {
    key: 0,
    name: "Test camera",
    ip: "Test ip",
    firmware: "Test firmware",
    lastSeen: "Test lastseen",
    owner: "Test owner",
    capabilities: ["Test capability1", "Test capability2"],
};

const setup = () => {
  wrapper = shallow(<CamListItem {...testProps} />);
};

test("<CamListItem /> renders without error", () => {
  setup();
});

test("<ReserveRelease /> component is rendered", () => {
   expect(wrapper.find(ReserveRelease)).toHaveLength(1);
});

describe('<CamListItem> component is rendered with...', () => {

  const checkExistingProp = (prop) => {
    expect(testProps).toHaveProperty(`${prop}`);
  };

  const check = (prop) => {
    checkExistingProp(prop);    
    const td = findByTestAttr(wrapper, `component-camlistitem-${prop}`);
    expect(td.text()).toEqual(testProps[`${prop}`]);
  };

  test("proper 'name' property", () => {
    const comp = findByTestAttr(wrapper, "component-camlistitem-tt-name");
    expect(comp.prop("name")).toBe(testProps["name"]);
  });
  
  test("proper 'ip' property", () => {
    check('ip');
  });
  
  test("proper 'firmware' property", () => {
    check('firmware');
  });
  
  test("proper 'lastSeen' property", () => {
    check('lastSeen');
  });
  
  test("proper 'owner' property", () => {
    check('owner');
  });  
});


test("<CamListItem /> component is rendered with test Capability", () => {
  const testCapabilities = findByTestAttr(wrapper,"component-camlistitem-captable");
  const outTestCapName = testCapabilities.getElement().props.children.props.table;
  const inTestCapName = testProps.capabilities;

  expect(outTestCapName).toEqual(inTestCapName);
});
