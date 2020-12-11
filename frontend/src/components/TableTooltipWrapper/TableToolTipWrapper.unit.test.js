import { shallow } from "enzyme";
import { findByTestAttr } from "../../test/testUtils";
import TableToolTipWrapper from "./TableToolTipWrapper"

test("<TableToolTipWrapper> renders correctly the wrapped element", () => {
  const testName = "test name";
  const wrapper = shallow(<TableToolTipWrapper name={"test name"} maxLength={15}/>);
  const comp = findByTestAttr(wrapper,"component-camlistitem-name");
  expect(comp.text()).toEqual(testName);
});
