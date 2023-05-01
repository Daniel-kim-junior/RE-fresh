import { useState, onLoad, getDepartment } from './hook.js';
import { getAnnualtListByDepartment } from '../../../../api/calendarApi.js'


export default function AsideDepartment() {
  const [annual, setAnnual] = useState([]);
  const [page, setPage] = useState({start: 0, end: 10});
  const { start, end } = page;

  

  onLoad(async () => {
    const department = getDepartment();
    const response = await getAnnualtListByDepartment(department, page.start, page.end);
    const list = makeAnnualList(response);
    setAnnual(list);
  })

  function makeAnnualList(annualList) {
    let dom = '<ul>';
    annualList.forEach((item) => {
      dom += `<li>${item.name}</li>
            <li>${item.startDate}</li>
            <li>${item.endDate}</li>
      `
    });
    dom += '</ul>';
    return dom;
  }
  return annual;
}

