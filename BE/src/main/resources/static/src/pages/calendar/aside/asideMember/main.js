import { $App } from "./app.js";
import { render, throttle } from "./hook.js";



(function inputMemberName() {
  const input = document.querySelector('#member-input');
  const aside = document.querySelector('#aside-contents');
  aside.innerHTML = '';
  aside.onscroll = null;
  input.onkeyup = throttle((e) => {
    const name = e.target.value;
    if (name === '') {
      aside.innerHTML = '';
      return;
    }
    render($App, aside, name);
    aside.style.display = 'block';
  }, 500);
})();





