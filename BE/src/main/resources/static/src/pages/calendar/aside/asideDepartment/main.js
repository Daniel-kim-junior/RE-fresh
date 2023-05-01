import { $App } from "./app.js";
import { render, setDepartment } from "./hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  const aside = document.querySelector('#aside');
  select.onchange = (e) => {
    const department = e.target.value;
    setDepartment();
    aside.innerHTML = '';
    aside.style.display = 'block';
    render($App, document.querySelector("#aside"), department);
  }
})();





