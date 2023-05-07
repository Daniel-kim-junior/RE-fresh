import { getDepartmentNameList, getDepartmentInit } from '../../../../api/calendarApi.js';
import { useState } from './hook.js';
import { onLoad } from './hook.js';

/*
  Daniel Kim

  CalendarHeader 를 생성하는 함수

  2023-05-01
*/

export default function DepartmentOptionList() {
  const [list, setList] = useState([]);

  onLoad(async () => {
    const depList = await getDepartmentNameList();
    const departmentInit = await getDepartmentInit();
    const dom = makeOptionList(depList, departmentInit['department']);
    setList(dom);
  });
  
  function makeOptionList(list, name) {
    let dom = `<option value="${name}">${name}</option>`;
    list.forEach((item) => {
      if (item !== name) {
        dom += `<option value="${item}">${item}</option>`;
      }   
    });
    dom += `<svg
    class="fill-current w-6 h-6 ml-2 department-btn"
    viewBox="0 0 20 20"
    xmlns="http://www.w3.org/2000/svg"
  >
    <path
      class="department-btn"
      d="M10 13a1 1 0 0 1-.707-.293l-3-3a1 1 0 1 1 1.414-1.414L10 10.586l2.293-2.293a1 1 0 1 1 1.414 1.414l-3 3A1 1 0 0 1 10 13z"
    />
  </svg>`;
    return dom;
  }

  return list;
}