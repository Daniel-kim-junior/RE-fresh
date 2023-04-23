/*
    Daniel Kim
    
    Calendar.html에 해당하는 js파일
    
    2023-04-16
*/
function CalendarHook() {
  const options = {
    currentStateKey: 0,
    renderCount: 0,
    states: [],
    root: null,
    rootComponent: null,
  }
  function useState(initState) {
    const { currentStateKey: key, states } = options;
    
    if (states.length === key) options.states.push(initState);
    
    const state = states[key];
  
    const setState = (newState) => {
      if (states[key] === newState) return;
      if (JSON.stringify(options.states[key]) === JSON.stringify(newState)) return;

      states[key] = newState;
      _render();
    }
    options.currentStateKey += 1;
    return [ state, setState ];
  }

  const _render = debounceFrame(() => {
    const { root, rootComponent } = options;
    if (!root || !rootComponent) return;
    root.innerHTML = rootComponent();
    options.currentStateKey = 0;
    options.renderCount += 1;
  });

  function render(rootComponent, root) {
    options.root = root;
    options.rootComponent = rootComponent;
    _render();
  }

  return { useState, render };
}


function debounceFrame(callback) {
  let nextFrameCallback = -1;
  return () => {
    cancelAnimationFrame(nextFrameCallback);
    nextFrameCallback = requestAnimationFrame(callback);
  }
}

export default function onLoad(callback) {
  let cleanup;
  let mounted = true;

  function cleanupWrapper() {
    if (cleanup) {
      cleanup();
      cleanup = null;
    }
  }

  function effect() {
    cleanupWrapper();
    cleanup = callback();

    return cleanupWrapper;
  }

  if (mounted) {
    effect();
  }

  return function cleanupEffect() {
    mounted = false;
    cleanupWrapper();
  };
}


export const { useState, render } = CalendarHook();