import { ContactSupportOutlined } from "@material-ui/icons";
import { shallow } from "enzyme";
import { findByTestAttr } from "../../../test/testUtils";
import CameraDetailsTable from './CameraDetailsTable';

const columnNames = ["Camera name:", "Password:","Camera IP:", "Camera S/N:", "Last seen:"];

const camData = [
  "Test cam",
  "Test pwd",
  "Test ip",
  "Test sn",    
  "1111-11-11",
  ];

test("<CameraDetailsTable> is rendering wothout error", ()=>{
  shallow(<CameraDetailsTable columnNames={["name"]} camData={"test name"}/>);
});

test("camera table is rendering every camera property",()=>{
  const wrapper = shallow(<CameraDetailsTable columnNames={columnNames} camData={camData}/>);
  const comp = findByTestAttr(wrapper, "component-cdt-td");
  comp.forEach((item)=>{
    expect(camData).toContain(item.text());
  });
});



