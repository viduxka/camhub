import { shallow } from "enzyme";
import SnackBar from "./SnackBar";
import { findByTestAttr } from '../../test/testUtils';

test("Snackbar renders without error",() => {
  const wrapper = shallow(
    <SnackBar
      open={true}
      message={""}
      onCloseAlert={()=>{}}
      backgroundColor={"red"}/>);
    const comp = findByTestAttr(wrapper, "comp-snackbar");
    expect(comp).toHaveLength(1); 
});
