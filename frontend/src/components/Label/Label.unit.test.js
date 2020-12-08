import { shallow } from "enzyme";
import Label from './Label';

let wrapper = {};

test("renders custom text in <Label/> without error", () => {
  wrapper = shallow(<Label/>);
});

test("renders custom text with appropriate properties", () => { 
  const testText = "Test";  
  wrapper = shallow(<Label>{testText}</Label>);   
  const labelProps = wrapper.getElement().props;
  expect(labelProps.className).toBe('roundedLabel');
  expect(labelProps.children).toBe(testText);  
});
