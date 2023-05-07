import { render } from "./hook.js";
import { $App } from "./app.js";
/*
  Daniel Kim

  render 함수를 호출하여 생성한 Dom을 App에 붙여주는 역할
  rendering()

  2023-04-24
*/
export const initDepartment = () => render($App, document.querySelector("#depart-cal-header"));
initDepartment();
