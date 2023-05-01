import { $App } from "./app.js";
import { render } from "./hook.js";
const INIT_LENGTH = 0;


(function setVisibleHeader() {
  const selectDom = document.querySelector('#search-option');
  const department = document.querySelector('#department');
  const member = document.querySelector('#member');
  const select = document.querySelector('#department-select');
  const aside = document.querySelector('#aside');
  const input = document.querySelector('#member-input');

  selectDom.onchange = (e) => {
    if (e.target.value === 'department') {
      department.style.display = 'block';
      if (select.childNodes.length === INIT_LENGTH) {
        render($App, select);
      }
      member.style.display = 'none';
    } else if (e.target.value === 'member') {
      input.value = '';
      member.style.display = 'block';
      department.style.display = 'none';
    } else {
      department.style.display = 'none';
      member.style.display = 'none';
    }
    aside.innerHTML = '';
  }
})();





