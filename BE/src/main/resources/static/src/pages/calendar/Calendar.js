import getCalendarData from '../../api/CalendarApi.js';
import { useState } from './MyHook.js';
import onLoad from './MyHook.js';

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
  const date = new Date();
  const y = date.getFullYear();
  const m = date.getMonth() + 1;
  const buttons = document.querySelector('#button-container');
  const header = document.querySelector('#calendar-header');


  /*
    Daniel Kim

    state 관리를 위해 선언한 변수
    calendar는 달력의 데이터를 담고 있는 state이다.
    mdy는 년도와 월을 담고 있는 state이다.
    year는 mdy의 연과 월을 담고 있는 state이다.

    2023-04-23
  */
  const [calendar, setCalendar] = useState([]);
  const [mdy, setMdy] = useState({year: y, month: m});
  const { year, month } = mdy;

  /*
    Daniel Kim

    버튼을 클릭했을 때 이전 달, 다음 달을 보여주기 위한 함수
    이전 달을 보여주기 위해서는 현재 달이 1월이면 년도를 -1 해주고
    월을 12월로 바꿔준다. (setState를 사용하여 state를 변경)
    다음 달을 보여주기 위해서는 현재 달이 12월이면 년도를 +1 해주고
    월을 1월로 바꿔준다. (setState를 사용하여 state를 변경)
    년도가 2023년보다 크거나 2021년보다 작으면 return 한다.(유효하지 않은 년도)

    2023-04-23
  */
  
  buttons.addEventListener('click', (e) => {
    if (e.target.id === 'prev') {
      if (month === 1) {
        if (inValidDate(year - 1)) return;
        setMdy({ year: year - 1, month: 12 });
      } else {
        setMdy({ year, month: month - 1 })
      }
    } else if (e.target.id === 'next') {
      if (month === 12) {
        if (inValidDate(year + 1)) return;
        setMdy({ year: year + 1, month: 1 });
      } else {
        setMdy({ year, month: month + 1 });
      }
    }
  });
  /*
    Daniel Kim

    달력의 헤더를 만들어주는 함수

    2023-04-23
  */
  function makeHeader(year, month) {
    return `<h1 class="text-2xl font-bold text-center text-gray-800 py-2">${year}년 ${month}월</h1>`
  }
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
      const response = await getCalendarData(year, month);
      const cal = makeCalendar(response);
      setCalendar(cal);
      header.innerHTML = makeHeader(year, month);
    } catch(error) {
      console.error(error);
    }
  });
  /*
    Daniel Kim

    calendar year, month가 현재 날짜보다 1년 이상 차이나거나
    현재 년도보다 작으면 true를 반환한다.(유효하지 않은 년도)

    2023-04-23
  */
  function inValidDate(year) {
    if (year > y + 1 || year < y) return true;
    return false;
  }
  /*
    Daniel Kim

    평일인지, 휴일인지, 공휴일인지 판단하는 함수

    2023-04-23
  */
  function isSpecialDay (hoName) {
    return hoName === '평일' || hoName === '' || hoName === '일요일'
    ? false : true;
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
        dom += `<td class="border-2 border-red-100 w-20 h-20">
          <div class="h-full block align-top text-left py-2 px-4">
            <div class=${fontStyle}>${calendar[i].day}</div>
              ${isSpecialDay(calendar[i].hoName) ? `<div class="text-xs">${calendar[i].hoName}</div>` : ''}
          </div>
        </td>`;
      } else {
      dom += `</tr><tr><td class=" border-2 border-red-100 w-20 h-20">
      <div class="h-full block align-top text-left py-2 px-4">
        <div class=${fontStyle}>${calendar[i].day}</div>
          ${isSpecialDay(calendar[i].hoName) ? `<div class="text-xs">${calendar[i].hoName}</div>` : ''}
        </div>
      </td>`;
      cnt++;
      }
    }
    dom += '</tr>'
    return dom;
  }

  return calendar;
}