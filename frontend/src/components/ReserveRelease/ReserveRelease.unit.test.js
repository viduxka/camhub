import { shallow } from "enzyme";
import { ReserveRelease } from "./ReserveRelease";

test("Reserve button renders without error", () => {
  shallow(<ReserveRelease />);
});

test("Reserve button has confirming properties", () => {
  const requirement = {
    variant: "outlined",
    color: "default",
    size: "small",
  };
  const wrapper = shallow(<ReserveRelease />);
  const properties = wrapper.getElement().props;
  expect(properties.variant).toEqual(requirement.variant);
  expect(properties.color).toEqual(requirement.color);
  expect(properties.size).toEqual(requirement.size);
});
