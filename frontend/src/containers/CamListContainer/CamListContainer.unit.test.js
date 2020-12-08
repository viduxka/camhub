import { shallow } from "enzyme";
import CamListContainer from "./CamListContainer";
import Discover from "../../components/Discover/Discover";
import { findByTestAttr } from "../../test/testUtils";

const camlistTableAttributes = [
  "camera name",
  "camera ip",
  "firmware",
  "last seen",
  "owner",
  "capabilities",
];

let tableHeaders = null;

const renderCamListContainer = () => shallow(<CamListContainer />);

const getCamlistTableHeader = () => {
  const wrapper = renderCamListContainer();
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

test("Discover button is created in CamListContainer", () => {
  const wrapper = shallow(<CamListContainer />);
  expect(wrapper.contains(<Discover />)).toBeTruthy();
});

test("renders all camera attributes to the table header", () => {
  const tableHdrs = [...tableHeaders];
  const cameraAttributes = tableHdrs.splice(1, tableHdrs.length);
  expect(cameraAttributes.map((item) => item.toLocaleLowerCase())).toEqual(
    camlistTableAttributes
  );
});

test("<Discover /> component is the first element of the table", () => {
  const discoveryComponentisPresent = [...tableHeaders][0] === "<Discover />";
  expect(discoveryComponentisPresent).toBeTruthy();
});

test("component displays proper cameras informations in the list", () => {
  const camOut = { cameras: [] };
  const testCameras = {
    cameras: [
      {
        name: "Vidux-1080p",
        ip: "10.30.0.21",
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
    ],
  };
  const wrapper = shallow(<CamListContainer />);
  const inst = wrapper.instance();
  inst.setState({ ...testCameras });
  const componentsFound = findByTestAttr(
    wrapper,
    "component-camlistcontainer-camlistitem"
  );
  componentsFound.forEach((cam) => {
    camOut.cameras.push({
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
