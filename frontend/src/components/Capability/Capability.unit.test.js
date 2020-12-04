import { shallow } from "enzyme";
import Capability from './Capability';
import Label from '../Label/Label';

let wrapper = null;
const testText = "Teszt";
const renderCapability = () => {
  wrapper = shallow(<Capability name={testText}/>);
};

test("Capability is rendered without errors", () => {
  renderCapability();
});

test("Capability creates Label component", () => {
  expect(wrapper.containsMatchingElement(Label)).toEqual(true);
});

test("Capability sets custom text in Label component",()=>{
  const wrapperLabel = wrapper.find(Label);
  expect(wrapperLabel.children().text()).toEqual(testText);
});
