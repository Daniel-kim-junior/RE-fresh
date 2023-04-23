import getCalendarData from '../../api/CalendarApi.js';
import { useState } from './CalendarHook.js';
import onLoad from './CalendarHook.js';

export default function Calendar() {
  const date = new Date();
  const y = date.getFullYear();
  const m = date.getMonth() + 1;
  const buttons = document.querySelector('#button-container');
  const header = document.querySelector('#calendar-header');
  const [calendar, setCalendar] = useState([]);
  const [year, setYear] = useState(y);
  const [month, setMonth] = useState(m);
  
  buttons.addEventListener('click', (e) => {
    if (e.target.id === 'prev') {
      if (month === 1) {
        if (inValidDate(year - 1)) return;
        setMonth(12);
        setYear(year - 1);
      } else {
        setMonth(month - 1);
        setYear(year);
      }
    } else if (e.target.id === 'next') {
      if (month === 12) {
        if (inValidDate(year + 1)) return;
        setMonth(1);
        setYear(year + 1);
      } else {
        setYear(year);
        setMonth(month + 1);
      }
    }
  });

  function makeHeader(year, month) {
    return `<h1 class="text-2xl font-bold text-center text-gray-800 py-2">${year}년 ${month}월</h1>`
  }

  onLoad(() => {
    async function fetchCal(year, month) {
      const response = await getCalendarData(year, month);
      const cal = makeCalendar(response);
      setCalendar(cal);
    }
    fetchCal(year, month);
    header.innerHTML = makeHeader(year, month);
  });

  function inValidDate(year) {
    if (year > y + 1 || year < y) return true;
    return false;
  }

  function isSpecialDay (hoName) {
    return hoName === '평일' || hoName === '' || hoName === '일요일'
    ? false : true;
  }

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