import { $App } from "./app.js";
import { render } from "./hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  select.onchange = (e) => {
    const department = e.target.value;
    aside.innerHTML = '';
    render($App, document.querySelector("#aside"), department);
    aside.style.display = 'block';
  }
})();





