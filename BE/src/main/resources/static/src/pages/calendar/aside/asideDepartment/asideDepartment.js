import { useState, onLoad, getDepartment, waitForRender, downChecked } from './hook.js';
import { getAnnualtListByDepartment } from '../../../../api/calendarApi.js'

let scrollFlag = false;
let scrollEnd = false;

export function deptScrollInit() {
  scrollEnd = false;
}


export default function AsideDepartment() {
  const asideContents = document.querySelector('#aside-contents');
  const loadingSpinner = document.querySelector('#aside-contents-status');
  const [annual, setAnnual] = useState([]);
  const [page, setPage] = useState({ start: 0, end: 10 });
  const { start, end } = page;
  const checked = downChecked();
  const date = new Date();
  console.log(start, end);

  onLoad(async () => {
    if (scrollEnd) return;
    const department = getDepartment();
    const response = await getAnnualtListByDepartment(department, start, end);
    if (response.length === 0) {
      scrollEnd = true;
      return;
    }
    const list = makeAnnualList(response);
    if (start === 0) {
      setAnnual(list);
    } else if(scrollFlag) {
      setAnnual(annual.concat(list));
      scrollFlag = false;
    }
  })


  waitForRender(asideContents, () => {
    asideContents.onscroll = (e) => {
      const el = e.target;
      const scrollHeight = el.scrollHeight;
      const scrollTop = el.scrollTop;
      const clinetHeight = el.clientHeight;
      if (scrollHeight - scrollTop === clinetHeight && !scrollEnd) {
        loadingSpinner.style.display = 'block';
        scrollFlag = true;
        setTimeout(() => {
          setPage({ ...page, start: start + 10 });
          loadingSpinner.style.display = 'none';
        }, 1500);
        
      }
    }
  });
  


  function makeAnnualList(annualList) {
    let dom = '<ul class="max-w-md divide-y divide-gray-200 dark:divide-gray-700">';
    for (let i = 0; i < annualList.length; i++) {
      if(checked) {
        if (Date.parse(annualList[i].endDate) < Date.parse(date)) {
          continue;
        }
      }
      dom += `<li class="pb-3 sm:pb-4">
      <div class="flex items-center space-x-4">
        <div class="flex-shrink-0">
          <img class="w-8 h-8 rounded-full" src="/src/assets/images/user.svg" alt="Neil image">
        </div>
        <div class="flex-1 min-w-0">
            <p class="text-xs font-medium text-gray-900 truncate dark:text-white">
               ${annualList[i].name}
            </p>
            <p class="text-xs text-gray-500 truncate dark:text-gray-400">
              ${annualList[i].email} 
            </p>
         </div>
         <div class="inline-flex items-center text-xs font-semibold text-gray-900 dark:text-white">
         ${annualList[i].startDate} ~ ${annualList[i].endDate}
        </div>
      </div>
      </li>
      `
    }
    dom += '</ul>';
    return dom;
  }
  return annual;
}

