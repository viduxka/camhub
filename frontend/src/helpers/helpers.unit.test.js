import { truncateNameIfLongerThan } from "./helpers";

test("truncate properly if param is null", () => {
  const testInput = null;
  const res = truncateNameIfLongerThan(testInput, 2);
  expect(res).toBeNull;
});

test("truncate properly if length is 0", () => {
  const testInput = "";
  const res = truncateNameIfLongerThan(testInput, 2);
  expect(res).toBeNull;
});

test("truncate properly if longer than 2", () => {
  const testInput = "abcde";
  const res = truncateNameIfLongerThan(testInput, 2);
  expect(res).toBe("ab...");
});

test("does not modify name if truncate length is longer than the param", () => {
  const testInput = "abcde";
  const res = truncateNameIfLongerThan(testInput, 20);
  expect(res).toBe("abcde");
});
