import { shallow } from "enzyme";
import Discover from './Discover';

test("Discover button is rendered without errors", () => {
  shallow(<Discover/>);
});