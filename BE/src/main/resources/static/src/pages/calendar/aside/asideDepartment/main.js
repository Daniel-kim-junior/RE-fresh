import { $App } from "./app.js";
import { deptScrollInit } from './asideDepartment.js';
import { render, initState } from "../hook/hook.js";



(function selectDepartment() {
  const select = document.querySelector('#department-select');
  const checkboxWrapperDept = document.querySelector('#checkbox-wrapper-dept');
  const checkboxForExceptDept = document.querySelector('#checkbox-for-except-dept');
  const aside = document.querySelector('#aside-contents');
  const loadingSpinner = document.querySelector('#aside-contents-status');

  checkboxForExceptDept.onchange = (e) => {
    aside.innerHTML = '';
    aside.onscroll = null;
    deptScrollInit();
//    initState();
    loadingSpinner.style.display = 'none';
    const department = select.value;
    if (!e.target.checked) {
      render($App, aside, department);
    } else {
      render($App, aside, department, true);
    }
    aside.style.display = 'block';
  }

  select.onchange = (e) => {
    aside.innerHTML = '';
    aside.onscroll = null;
    deptScrollInit();
//     initState();
    checkboxForExceptDept.checked = false;
    checkboxWrapperDept.style.display = 'block';

    loadingSpinner.style.display = 'none';
    const department = e.target.value;

    if (!checkboxForExceptDept.checked) {
      render($App, aside, department);
    } else {
      render($App, aside, department, true);
    }
    aside.style.display = 'block';
  }
})();





