import { shallow } from "enzyme";
import Capability from "../Capability/Capability";
import CapabilityTable from './CapabilityTable';
import { findByTestAttr } from '../../test/testUtils';

let wrapper = {};

test("capability table renders without error", () => {
  shallow(<CapabilityTable/>);
});

test("capability table sets custom capabilities",() => {
  const tesztLabels =["Teszt1", "Teszt2"];
  wrapper = shallow(<CapabilityTable table={tesztLabels}/>);
  const wrapperCap = findByTestAttr(wrapper, "component-capability-td");
  
  wrapperCap.forEach(cap => 
    expect(tesztLabels).toContain(cap.prop('name'))
  );
});

test("capability table does not create undefined capability",() => {
  wrapper = shallow(<CapabilityTable table={undefined}/>);
  expect(wrapper.containsMatchingElement(<Capability />)).toEqual(false);
});