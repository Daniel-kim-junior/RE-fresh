function Util() {

  function throttle(callback, delay) {
    let timeId = null;
    return function (...args) {
      clearTimeout(timeId);
      timeId = setTimeout(() => {
        callback.apply(this, args);
      }, delay);
    }
  }

  function waitForRender(elem, callback) {
    requestAnimationFrame(() => {
      var isRenderedReady = elem.offsetHeight > 0;
      if (isRenderedReady) {
        callback();
      } else {
        waitForRender(elem, callback);
      }
    });
  }

  function onLoad(callback) {
    callback();
  }

  function debounceFrame(callback) {
    let nextFrameCallback = -1;
    return () => {
      cancelAnimationFrame(nextFrameCallback);
      nextFrameCallback = requestAnimationFrame(callback);
    }
  }


  return { throttle, waitForRender, onLoad, debounceFrame };

}



export const { throttle, waitForRender, onLoad, debounceFrame } = Util();
