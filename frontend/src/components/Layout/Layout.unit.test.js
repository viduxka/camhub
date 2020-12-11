import { shallow } from 'enzyme';
import Layout from './Layout';

test("Layout renders without error",() => {
  shallow(<Layout/>);
});

test("Layout renders main with proper children",() => {
  const inText = "Test";
  const wrapper = shallow(<Layout>{inText}</Layout>);
  expect(wrapper.find('main').children().text()).toEqual(inText); 
});
