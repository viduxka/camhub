import { shallow } from "enzyme";
import Discover from './Discover';
import { findByTestAttr } from '../../test/testUtils';

test("Discover button is rendered without errors", () => {
  shallow(<Discover/>);
});

test("Discover button is rendered with proper text", () => {
  const wrapper = shallow(<Discover/>);
  const comp = findByTestAttr(wrapper, "comp-discover");
  expect(comp.text()).toEqual("Discover");
});