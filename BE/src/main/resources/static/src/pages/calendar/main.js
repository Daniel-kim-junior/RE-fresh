import { render } from "./MyHook.js";
import { calendar } from "./App.js";

/*
  Daniel Kim

  render 함수를 호출하여 생성한 Dom을 App에 붙여주는 역할
  rendering()

  2023-04-24
*/
render(calendar, document.querySelector("#App"));