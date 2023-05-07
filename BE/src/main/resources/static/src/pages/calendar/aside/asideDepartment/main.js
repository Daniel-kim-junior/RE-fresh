import { $App } from "./app.js";
import { render } from "./hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  const checkboxForExcept = document.querySelector('#checkbox-for-except');
  

  select.onchange = (e) => {
    const department = e.target.value;
    console.log(checkboxForExcept.checked);
    render($App, document.querySelector('#aside-contents'), department);
  }
})();





