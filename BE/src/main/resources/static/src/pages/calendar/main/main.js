import { render } from "./hook.js";
import { $App } from "./app.js";
/*
  Daniel Kim

  render 함수를 호출하여 생성한 Dom을 App에 붙여주는 역할
  rendering()

  2023-04-24
*/
const main = document.querySelector("#main");
const departCalHeader = document.querySelector("#depart-cal-header");
departCalHeader.onchange = (e) => {
  render($App, main , e.target.value);
}
render($App, main);


