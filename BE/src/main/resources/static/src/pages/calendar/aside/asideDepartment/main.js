import { $App } from "./app.js";
import { render } from "./hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  const checkboxForExcept = document.querySelector('#checkbox-for-except');
  const aside = document.querySelector('#aside-contents');

  select.onchange = (e) => {
    aside.innerHTML = '';
    const department = e.target.value;
    if (checkboxForExcept.checked) {
      render($App, document.querySelector('#aside-contents'), department);
    } else {
      render($App, document.querySelector('#aside-contents'), department, true);
    }
  }
})();





