import { $App } from "./app.js";
import { render } from "./hook.js";
import { optionInit } from "../asideDepartment/hook.js";
import { memberScrollInit } from '../asideMember/asideMember.js';
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

  const checkboxWrapperDept = document.querySelector('#checkbox-wrapper-dept');
  const checkboxWrapperMember = document.querySelector('#checkbox-wrapper-member');
  const checkboxForExceptDept = document.querySelector('#checkbox-for-except-dept');
  const checkboxForExceptMember = document.querySelector('#checkbox-for-except-member');


  selectDom.onchange = (e) => {
    checkboxWrapperDept.style.display = 'none';
    checkboxWrapperMember.style.display = 'none';
    aside.onscroll = null;
    aside.innerHTML = '';
    loadingSpinner.style.display = 'none';
    checkboxForExceptDept.checked = false;
    checkboxForExceptMember.checked = false;
    if (e.target.value === 'department') {
      optionInit();
      departmentSelect.value = '부서 선택';
      department.style.display = 'block';
      if (select.childNodes.length === INIT_LENGTH) {
        render($App, select);
      }
      member.style.display = 'none';
    } else if (e.target.value === 'member') {
      input.value = '';
      memberScrollInit();
      checkboxWrapperDept.style.display = 'none';
      checkboxWrapperMember.style.display = 'block';
      member.style.display = 'block';
      department.style.display = 'none';
    } else {
      checkboxWrapperDept.style.display = 'none';
      checkboxWrapperMember.style.display = 'none';
      department.style.display = 'none';
      member.style.display = 'none';
    }
  }
})();





