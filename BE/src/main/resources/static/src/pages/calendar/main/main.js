import { render } from "./hook.js";
import { $App } from "./app.js";
/*
  Daniel Kim

  render 함수를 호출하여 생성한 Dom을 App에 붙여주는 역할
  rendering()

  2023-04-24
*/
render($App, document.querySelector("#cal-header"));
/*
  Daniel Kim

  render 함수를 호출하여 생성한 Dom을 App에 붙여주는 역할
  rendering()

  2023-04-24
*/

const todayBtn = document.querySelector("#today-btn");
const main = document.querySelector("#main");
const calHeader = document.querySelector("#cal-header");

calHeader.onchange = (e) => {
  render($App, main , e.target.value);
}
render($App, main);


