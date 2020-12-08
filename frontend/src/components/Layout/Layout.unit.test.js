import { shallow } from 'enzyme';
import Layout from './Layout';

test("Layout renders without error",() => {
  shallow(<Layout/>);
});

test("Layout renders with proper children",() => {
  const inText = "Test";
  const wrapper = shallow(<Layout>{inText}</Layout>);
  expect(wrapper.find('div')).toHaveLength(1);
  expect(wrapper.find('main').children().text()).toEqual(inText); 
});
