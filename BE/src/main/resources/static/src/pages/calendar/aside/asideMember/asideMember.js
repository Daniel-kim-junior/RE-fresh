import { useState, onLoad, getName } from './hook.js';
import { getAnnualListByMember } from '../../../../api/calendarApi.js'


export default function AsideMember() {
  const [annual, setAnnual] = useState([]);
  const [page, setPage] = useState({start: 0, end: 10});
  const { start, end } = page;

  onLoad(async () => {
    const memberName = getName();
    const response = await getAnnualListByMember(memberName, start, end);
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

