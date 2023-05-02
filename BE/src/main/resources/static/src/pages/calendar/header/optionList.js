import { getDepartmentNameList, getDepartmentInit } from '../../../api/calendarApi.js';
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
    const departmentInit = await getDepartmentInit();
    const dom = makeOptionList(depList, departmentInit['department']);
    setList(dom);
  });
  function makeOptionList(list, name) {
    let dom = `<option>${name}</option>`;
    list.forEach((item) => {
      if (item !== name) {
        dom += `<option value="${item}">${item}</option>`;
      }   
    });
    return dom;
  }

  return list;
}