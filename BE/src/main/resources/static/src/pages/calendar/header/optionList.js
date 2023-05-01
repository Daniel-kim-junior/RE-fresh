import { getDepartmentNameList } from '../../../api/calendarApi.js';
import { useState } from './hook.js';
import { onLoad } from './hook.js';

/*
  Daniel Kim

  CalendarHeader 를 생성하는 함수

  2023-05-01
*/

export default function OptionList() {
  const [list, setList] = useState([]);

  onLoad(async () => {
    const depList = await getDepartmentNameList();
    const dom = makeOptionList(depList);
    setList(dom);
  });
  function makeOptionList(list) {
    return list.map((item) => `
      <option value="${item}">${item}</option>
    `).join('');
  }

  return list;
}