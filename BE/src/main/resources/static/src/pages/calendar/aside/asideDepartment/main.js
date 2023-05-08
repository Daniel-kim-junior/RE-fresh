import { $App } from "./app.js";
import { render, optionInit } from "./hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  const checkboxForExcept = document.querySelector('#checkbox-for-except');
  const aside = document.querySelector('#aside-contents');
  const loadingSpinner = document.querySelector('#aside-contents-status');

  select.onchange = (e) => {
    aside.innerHTML = '';
    aside.onscroll = null;
    optionInit();
    loadingSpinner.style.display = 'none';
    const department = e.target.value;
    render($App, aside, department);
    aside.style.display = 'block';
  }
})();





