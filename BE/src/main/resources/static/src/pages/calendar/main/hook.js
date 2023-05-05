/*
    Daniel Kim
    
    CalendarHook
    view 계층에서 사용할 render 함수와
    상태 관리를 위한 useState 함수를 제공하는 모듈
    useState는 상태와 상태를 변경하는 함수를 반환한다.
    
    2023-04-16
*/
function Hook() {
  /*
    Daniel Kim
   
    options 객체로 상태 관리와 렌더링을 위한 변수를 관리한다
    - currentStateKey: 현재 상태의 키값(초기값 : 0)
    - renderCount: 렌더링 횟수(초기값 : 0)
    - states: 상태를 저장하는 배열(초기값 : [])
    - root: 렌더링할 루트 요소(초기값 : null)
    - rootComponent: 렌더링할 컴포넌트(초기값 : null)

    2023-04-16
   */
  const options = {
    currentStateKey: 0,
    renderCount: 0,
    states: [],
    root: null,
    firstRender: null,
    departmentName: null,
    rootComponent: null,
  }
  function setDepartmentName(departmentName) {
    options.departmentName = departmentName;
  }



  /*
    Daniel Kim

    useState 함수는 상태와 상태를 변경하는 함수를 반환한다.
    states의 길이와 key가 같으면 상태를 추가한다.
    상태를 변경하는 함수는 상태가 변경되었을 때만 렌더링을 수행한다.
    마지막에 현재 currentStateKey를 1 증가시킨다.

    2023-04-16
   */
  function useState(initState) {
    const { currentStateKey: key, states } = options;
    
    if (states.length === key) states.push(initState);
    
    const state = states[key];
  
    const setState = (newState) => {
      if (state === newState) return;
      if (JSON.stringify(state) === JSON.stringify(newState)) return;

      states[key] = newState;
      _render();
    }
    options.currentStateKey += 1;
    return [state, setState];
  }


  /*
    Daniel Kim

    debounceFrame 함수는 렌더링 최적화를 위해 사용한다.
    이전 frame의 callback을 취소하고 다음 frame의 callback을 실행한다.
    cancelAnimationFrame 함수는 nextFrameCallback request id를 취소한다.
    requestAnimationFrame 함수는 callback 함수를 다음 frame에 실행한다.

    2023-04-23
*/
function debounceFrame(callback) {
  let nextFrameCallback = -1;
  return () => {
    cancelAnimationFrame(nextFrameCallback);
    nextFrameCallback = requestAnimationFrame(callback);
  }
}
  /*
    Daniel Kim

    state가 변경되었을 때 or 초기 렌더링 때 
    실제 렌더링을 수행하는 함수
    debounceFrame 함수를 사용하여 렌더링 최적화를 해주었다.

    2023-04-16
  */
  const _render = debounceFrame(() => {
    const { root, rootComponent } = options;
    if (!root || !rootComponent) return;
    root.innerHTML = rootComponent();
    if (options.renderCount === 0 || options.renderCount === 1) {
      options.firstRender = root.innerHTML;
    }
    options.currentStateKey = 0;
    options.renderCount += 1;
    waitForRender(root, MockHide);      
  });

  function waitForRender(elem, callback, ...args) {
    requestAnimationFrame(() => {
      var isRendered = elem.offsetHeight > 0;
      if (isRendered) {
        if (args.length > 0) {  
          callback(...args);
        } else {
          callback();
        }
      } else {
        waitForRender(elem, callback, ...args);
      }
    });
  }

  function getRenderCount() {
    return options.renderCount;
  }

  function getDepartmentName() {
    return options.departmentName;
  }


  /*
    Daniel Kim

    render 함수는 루트 요소와 루트 컴포넌트를 받아서
    options 객체에 저장한다.
    _render 함수를 호출하여 실제 렌더링을 수행한다.
    모듈화와 debounceFrame 함수를 사용하기 위해 함수를 분리하였다.

    2023-04-16
  */
  function render(rootComponent, root, departmentName) {
    options.root = root;
    options.rootComponent = rootComponent;
    if (departmentName) {
      options.departmentName = departmentName; 
    }
    setTimeout(() => {
      _render();
    }, 300);
  }

  function getDate() {
    const date = new Date();
    const y = date.getFullYear();
    const m = date.getMonth() + 1;
    const d = date.getDate();

    return {y, m, d};
  }


  return { useState, render, getRenderCount, getDepartmentName, setDepartmentName, getDate, debounceFrame, waitForRender};
}

function MockHide() {
  const mockTable = document.getElementById("mock-table");
  const prevBtn = document.getElementById("prev-btn");
  const nextBtn = document.getElementById("next-btn");
  const mockHeader = document.getElementById("mock-header");

  prevBtn.innerHTML = '<';
  nextBtn.innerHTML = '>';
  prevBtn.style.fontSize = '30px';
  nextBtn.style.fontSize = '30px';
  prevBtn.disabled = false;
  nextBtn.disabled = false;
  mockHeader.style.display = 'none';
  mockTable.style.display = 'none';
}







/*
  Daniel Kim

  debounceButtonEvent 함수는 버튼 이벤트를 최적화하기 위해 사용한다.
  이전 timer를 취소하고 다음 timer를 실행한다.
  clearTimeout 함수는 timer를 취소한다.
  setTimeout 함수는 callback 함수를 delay 시간 후에 실행한다.

  2023-04-24
*/
function debounceButtonEvent(callback, delay, context) {
  let timer = null;
  return () => {
    const args = arguments;
    clearTimeout(timer);
    timer = setTimeout(() => {
      callback.apply(context, args);
    }, delay);
  }
}
/*
  Daniel Kim

  onLoad 함수는 callback 함수를 실행하여 Rest API를 호출
  state를 변경한다.

  2023-04-23
*/
function onLoad(callback) { 
  callback();
}

export { onLoad, debounceButtonEvent };
export const { useState, render, getRenderCount, getDepartmentName, setDepartmentName, getDate, debounceFrame, waitForRender } = Hook();