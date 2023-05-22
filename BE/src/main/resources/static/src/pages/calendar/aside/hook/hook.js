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
    name: null,
    checked: false,
    department: null,
    rootComponent: null,
  }
  function getName() {
    return options.name;
  }
  function initState() {
    options.currentStateKey = 0;
    options.renderCount = 0;
    options.states = [];
    options.root = null;
    options.rootComponent = null;
  }

  function getStates() {
    return options.states;
  }

  function getChecked() {
    return options.checked;
  }

  function getDepartment() {
    return options.department;
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

    render 함수는 루트 요소와 루트 컴포넌트를 받아서
    options 객체에 저장한다.
    _render 함수를 호출하여 실제 렌더링을 수행한다.
    모듈화와 debounceFrame 함수를 사용하기 위해 함수를 분리하였다.

    2023-04-16
  */
    function render(rootComponent, root, ...args) {
      options.root = root;
      options.rootComponent = rootComponent;
      const [name, checked, department] = args;
      options.name = name;
      options.checked = checked;
      options.department = department;
  
      _render();
    }

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
      options.currentStateKey = 0;
      options.renderCount += 1;
    });


  

  return { useState, render, getName, getStates, getChecked, getDepartment, initState };
}




export const {useState, render, getName, getStates, getChecked ,getDepartment, initState} = Hook();