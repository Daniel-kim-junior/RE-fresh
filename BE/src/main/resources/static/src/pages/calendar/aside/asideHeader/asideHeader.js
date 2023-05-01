import { useState, onLoad } from './hook.js';
import { getDepartmentNameList } from '../../../../api/calendarApi.js'


export default function AsideHeader() {
  
  const [departmentList, setDepartmentList] = useState([]);

  onLoad(async () => {
    const response = await getDepartmentNameList();
    const list = makeHeader(response);
    setDepartmentList(list);
  })

  function makeHeader(departmentList) {
    let dom = '';
    departmentList.forEach((item) => {
      dom += `<option value="${item}">${item}</option>`
    });
    return dom;
  }
  return departmentList;
}

