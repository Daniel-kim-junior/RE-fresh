import { $App } from "./app.js";
import { render } from "./hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  select.onchange = (e) => {
    const department = e.target.value;
    render($App, document.querySelector('#aside-contents'), department);
  }
})();





