import { $App } from "./app.js";
import { memberScrollInit } from './asideMember.js';
import { render, throttle } from "./hook.js";



(function inputMemberName() {
  const input = document.querySelector('#member-input');
  const aside = document.querySelector('#aside-contents');
  const checkboxForExceptMember = document.querySelector('#checkbox-for-except-member');

  checkboxForExceptMember.checked = false; 
  
  checkboxForExceptMember.onchange = (e) => {
    aside.innerHTML = '';
    aside.onscroll = null;
    memberScrollInit();
    const name = input.value;
    if (name === '') return;

    if (!e.target.checked) {
      render($App, aside, name);
    } else {
      render($App, aside, name, true);
    }
    aside.style.display = 'block';
  }


  
  aside.innerHTML = '';
  aside.onscroll = null;
  input.onkeyup = throttle((e) => {
    const name = e.target.value;
    memberScrollInit();
    if (name === '') {
      aside.innerHTML = '';
      return;
    }
    if (checkboxForExceptMember.checked) {
      render($App, aside, name, true);
    } else {
      render($App, aside, name);
    }
    aside.style.display = 'block';
  }, 500);
})();





