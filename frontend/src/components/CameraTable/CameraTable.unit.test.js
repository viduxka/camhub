import { shallow, mount } from "enzyme";
import { findByTestAttr } from "../../test/testUtils";
import CameraTable from './CameraTable';

const camlistTableAttributes = [
  "camera name",
  "camera ip",
  "firmware",
  "last seen",
  "owner",
  "capabilities",
];

let tableHeaders = null;

const renderCameraTable = () => shallow(<CameraTable />);

const getCamlistTableHeader = () => {
  const wrapper = renderCameraTable();
  const headers = findByTestAttr(wrapper, "component-camlistcontainer-th");
  const tableHeaders = headers.map((col) => col.text());
  return tableHeaders;
};

const setup = () => {
  tableHeaders = getCamlistTableHeader();
};

beforeAll(() => {
  setup();
});

test("renders all camera attributes to the table header", () => {
  const tableHdrs = [...tableHeaders];

  const cameraAttributes = tableHdrs.splice(1, tableHdrs.length);
  expect(cameraAttributes.map((item) => item.toLocaleLowerCase())).toEqual(
    camlistTableAttributes
  );
});


test("component displays proper cameras informations in the list", () => {
  const camOut = [];

  const mock = () => {};
  const testCameras = [
      {
        name: "Vidux-1080p-longlong-name",
        ip: "10.30.0.51",
        firmware: "5.4.21",
        lastSeen: "2020-01-10 13:13",
        owner: "gbene",
        capabilities: ["WDR", "1080p", "PoE"],
      },
      {
        name: "Vidux-720p",
        ip: "10.30.0.22",
        firmware: "5.4.21",
        lastSeen: "2020-01-11 11:13",
        owner: "nmityok",
        capabilities: ["WDR", "720p", "PoE"],
      },
    ];
  const wrapper = shallow(<CameraTable cameras={testCameras} selectCameraHandler={mock}/>);
  const inst = wrapper.instance();
/*   inst.setState({ ...testCameras }); */
  const componentsFound = findByTestAttr(
    wrapper,
    "component-camlistcontainer-camlistitem"
  );

  componentsFound.forEach((cam) => {
    camOut.push({
      name: cam.prop("name"),
      ip: cam.prop("ip"),
      firmware: cam.prop("firmware"),
      lastSeen: cam.prop("lastSeen"),
      owner: cam.prop("owner"),
      capabilities: cam.prop("capabilities"),
    });
  });

  expect(testCameras).toEqual(camOut);
});
