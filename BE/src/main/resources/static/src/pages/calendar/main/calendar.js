import { getCalendarDataByDepartment, getDepartmentInit} from '../../../api/calendarApi.js';
import { useState, getRenderCount, getDepartmentName, setDepartmentName, waitForRender} from './hook.js';
import { getDate, onLoad, debounceButtonEvent} from './hook.js';
import { initDepartment } from "../header/departmentOption/main.js";

/*
  Daniel Kim
  Calendar 함수 즉 dom을 리턴하는 함수
  2023-04-23
*/

export default function Calendar() {
  /*
    Daniel Kim
    현재 날짜를 기준으로 달력을 만들기 위해 선언한 date
    date.getFullYear()는 현재 년도를 반환한다.
    date.getMonth()는 현재 월을 반환한다.
    buttons는 이전 달, 다음 달 버튼을 담고 있는 요소이다.
    header는 달력의 헤더를 담고 있는 요소이다.
    2023-04-23
  */
  const { y, m, d } = getDate();

  const buttons = document.querySelector('#header-container');
  const header = document.querySelector('#calendar-header');
  const calArrowSvg = document.querySelector('.modal-open-close');
  const calArrowModal = document.querySelector('#cal-arrow-modal');
  const innerYear = document.querySelector("#inner-year");
  const innerMonth = document.querySelector("#inner-month");
  /*
    Daniel Kim
    state 관리를 위해 선언한 변수
    calendar는 달력의 데이터를 담고 있는 state이다.
    mdy는 년도와 월을 담고 있는 state이다.
    year는 mdy의 연과 월을 담고 있는 state이다.
    2023-04-23
  */
  const [calendar, setCalendar] = useState([]);
  const [mdy, setMdy] = useState({ year: y, month: m });
  const { year, month } = mdy;
  /*
    Daniel Kim
    Calendar 함수가 호출될 때 실행되는 함수
    getCalendarData 함수를 호출하여 달력 데이터를 가져온다.
    가져온 데이터를 makeCalendar 함수를 통해 달력을 만든다.
    달력을 만들고 나서 setCalendar 함수를 통해 calendar state를 변경한다.
    header에 달력의 헤더를 추가한다.
    2023-04-23
  */
  onLoad(async () => {
    try {
      let response;
      const count = getRenderCount();
      if (count === 1 || count === 0) {
        const departmentInit = await getDepartmentInit();
        response = await getCalendarDataByDepartment(year, month, departmentInit['department']);
        setDepartmentName(departmentInit['department']);
      } else {
        response = await getCalendarDataByDepartment(year, month, getDepartmentName()); 
      } 
      innerYear.innerHTML = makeModal('year', year, month);
      innerMonth.innerHTML = makeModal(false, year, month);
      const cal = makeCalendar(response, year, month);
      setCalendar(cal);
      waitForRender(innerYear, calScrollAndEvent, 'year', year, innerYear);
      waitForRender(innerMonth, calScrollAndEvent, 'month', month, innerMonth);
      header.innerHTML = makeHeader(year, month);
    } catch (error) {
      console.error(error);
    }
  });
  
  function calScrollAndEvent(...args) {
    const [trigger, value, dom] = args;
    const valueOffset = document.querySelector(`#${trigger}-${value}`).offsetTop;
    dom.scrollTo({ top: valueOffset, behavior: 'smooth' });
  }


  /*
    Daniel Kim
    버튼을 클릭했을 때 이전 달, 다음 달을 보여주기 위한 함수들
    이전 달을 보여주기 위해서는 현재 달이 1월이면 년도를 -1 해주고
    월을 12월로 바꿔준다. (setState를 사용하여 state를 변경)
    다음 달을 보여주기 위해서는 현재 달이 12월이면 년도를 +1 해주고
    월을 1월로 바꿔준다. (setState를 사용하여 state를 변경)
    년도가 2023년보다 크거나 2021년보다 작으면 return 한다.(유효하지 않은 년도)
    2023-04-23
  */
  const handlePrevClick = () => {
    if (month === 1) {
      setMdy({ year: year - 1, month: 12 });
    } else {
      setMdy({ year: year, month: month - 1 });
    }
  }
  
  const handleNextClick = () => {
    if (month === 12) {
      setMdy({ year: year + 1, month: 1 });
    } else {
      setMdy({ year: year, month: month + 1 });
    }
  }

  



    
  /*
    Daniel Kim
    버튼을 클릭했을 때 이전 달, 다음 달을 보여주기 위한 함수
    이전 달의 버튼을 눌렀을 때 함수 호출을 debounceButtonEvent 함수를 통해
    250ms 이내에 또 다른 이전 달 버튼을 누르면 시간 경과 후에 처리한다.
    (250ms 경과하면 이벤트들을 한번에 처리)
    2023-04-24
  */
  
  
  buttons.onclick = (e) => {
    if (e.target.id === 'prev-btn') {
      debounceButtonEvent(handlePrevClick, 250, this)();
    } else if (e.target.id === 'next-btn') {
      debounceButtonEvent(handleNextClick, 250, this)();
    } else if (e.target.id === 'today-btn') {
      if (y == year && m == month) return;
      initDepartment();
      debounceButtonEvent(() => setMdy({ year: y, month: m }), 250, this)();
    } else if (isArrowModal(e)) {
      calArrowSvg.classList.toggle('rotate-180');
      calArrowModal.style.display = isDisplayModal(); 
    }
  }
  
  calArrowModal.onclick = (e) => {
    if (isModalBtn(e)) {
      const select = Number(e.target.innerText.substring(0, e.target.innerText.length - 1));
      if (select === year) return;
      if (select === month) return;
      if (select < 13) {
        setMdy({...mdy, month: select});
      } else {
        setMdy({ ...mdy, year: select});
      }
    } else if (isModalExitBtn(e)) {
      calArrowModal.style.display = isDisplayModal();
      calArrowSvg.classList.toggle('rotate-180');
    }
  }

  function isModalBtn(e) {
    return e.target.classList.contains('modal-year-month');
  }


  function isModalExitBtn(e) {
    if(e.target.classList.contains('modal-exit-btn')) return true;
  }

  function isDisplayModal() {
    return calArrowModal.style.display === 'none' ? 'flex ' : 'none';
  }

  function isArrowModal(e) {
    return e.target.classList.contains('modal-open-close');
  }

  function makeModal(flag, ...args) {
    const [year, month] = args;
    return flag ? `${makeModalContents(1900, 2037, '년', year)}` : `${makeModalContents(1, 12, '월', month)}`;
  }

  function makeModalContents(start, end, str, value) {
    let dom = '';
    for (let i = start; i <= end; i++) {
      dom += `<li id=${str === '월' ? `month-${i}` : `year-${i}`} class="block p-1.5 hover:bg-sky-200 modal-year-month ${str === '년' && i === value ? 'bg-sky-200' : ''}
      ${str === '월' && i === value ? 'bg-sky-200' : ''}" value=${i}
      >
      <a href="javascript:;" class="block w-full modal-year-month">
      ${i + str}</a>
      </li>`;
    }
    return dom;
  }




  /*
    Daniel Kim
    달력의 헤더를 만들어주는 함수
    2023-04-23
  */
  function makeHeader(year, month) {
    return `<p class="text-2xl whitespace-nowrap font-bold w-full text-center text-gray-800 py-2">${year}년 ${month}월</p>`
  }


  
  /*
    Daniel Kim
    평일인지, 휴일인지, 공휴일인지 판단하는 함수
    2023-04-23
  */
  function isSpecialDay(hoName) {
    return hoName === '평일' || hoName === '' || hoName === '일요일'
      ? false : true;
  }
  

  function isToday(obj) {
    return obj.hoName !== '' && year === y && month === m  && obj.day === d;
  }


  /*
    Daniel Kim
    달력 dom을 만들어 주는 함수
    2023-04-23
  */
  function makeCalendar(calendar) {
    let dom = '<tr>';
    let cnt = 0;
    let fontStyle;
    for (let i = 0; i < calendar.length; i++) {
      if (calendar[i].hoName == '') {
        fontStyle = 'text-not-current';
      } else if (calendar[i].hoName === '평일') {
        fontStyle = 'text-current';
      } else {
        fontStyle = 'text-holiday';
      }

      if (parseInt(i / 7) === cnt) {
        dom += `<td class="${isToday(calendar[i]) ? 'bg-today-color' : ''} border border-table-rgba w-td-width h-td-height relative">
            <div class='absolute ${fontStyle} top-2 left-3'>${calendar[i].day}</div>
              ${isSpecialDay(calendar[i].hoName) ? `<div class="text-xs absolute top-3 left-12">${calendar[i].hoName}</div>` : ''}
            ${calendar[i].sumCount !== 0 ?`<div id="annual-day" class="text-sm/3 absolute bottom-3 right-3">🧑🏻‍❤‍💋‍🧑🏽${calendar[i].sumCount}명</div>` : ''}
        </td>`;
        
      } else {
        dom += `</tr><tr><td class="${isToday(calendar[i]) ? 'bg-today-color' : ''} border border-table-rgba w-td-width h-td-height relative">
        <div class='absolute ${fontStyle} top-2 left-3'>${calendar[i].day}</div>
          ${isSpecialDay(calendar[i].hoName) ? `<div class="text-xs absolute top-3 left-12">${calendar[i].hoName}</div>` : ''}
          ${calendar[i].sumCount !== 0 ? `<div id="annual-day" class="text-sm/3 absolute bottom-3 right-3">🧑🏻‍❤‍💋‍🧑🏽${calendar[i].sumCount}명</div>` : ''}
      </td>`;
        cnt++;
      } 
    }
    dom += '</tr>';
    return dom;
  }


  return calendar;
}