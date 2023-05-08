import { $App } from "./app.js";
import { render } from "./hook.js";
import { optionInit } from "../asideDepartment/hook.js";
const INIT_LENGTH = 0;


(function setVisibleHeader() {
  const selectDom = document.querySelector('#search-option');
  const department = document.querySelector('#department');
  const member = document.querySelector('#member');
  const departmentSelect = document.querySelector('#department-select');
  const select = document.querySelector('#department-select');
  const aside = document.querySelector('#aside-contents');
  const input = document.querySelector('#member-input');
  const loadingSpinner = document.querySelector('#aside-contents-status');
  const checkboxWrapper = document.querySelector('#checkbox-wrapper');
  const checkboxForExcept = document.querySelector('#checkbox-for-except');

  checkboxForExcept.checked = false;

  selectDom.onchange = (e) => {
    checkboxWrapper.style.display = 'none';
    aside.onscroll = null;
    aside.innerHTML = '';
    loadingSpinner.style.display = 'none';

    if (e.target.value === 'department') {
      optionInit();
      checkboxWrapper.style.display = 'block';
      departmentSelect.value = '부서 선택';
      department.style.display = 'block';
      aside.style.display = 'block';
      if (select.childNodes.length === INIT_LENGTH) {
        render($App, select);
      }
      member.style.display = 'none';
    } else if (e.target.value === 'member') {
      input.value = '';
      member.style.display = 'block';
      department.style.display = 'none';
    } else {
      checkboxForExcept.style.display = 'none';
      department.style.display = 'none';
      member.style.display = 'none';
    }
  }
})();





