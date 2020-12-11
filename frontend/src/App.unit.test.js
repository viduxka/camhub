import App from "./App";

import { shallow } from "enzyme";

test("renders App without error", () => {
  shallow(<App />);
});
