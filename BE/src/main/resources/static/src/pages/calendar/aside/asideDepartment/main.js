import { $App } from "./app.js";
import { render } from "./hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  const checkboxForExcept = document.querySelector('#checkbox-for-except');
  const aside = document.querySelector('#aside-contents');
  aside.onscroll = null;
  select.onchange = (e) => {
    aside.innerHTML = '';
    const department = e.target.value;
    render($App, aside, department);
    aside.style.display = 'block';
  }
})();





