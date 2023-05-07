import { useState, onLoad, getDepartment, waitForRender, downChecked } from './hook.js';
import { getAnnualtListByDepartment } from '../../../../api/calendarApi.js'

let scrollFlag = false;
export default function AsideDepartment() {
  const asideContents = document.querySelector('#aside-contents');
  const loadingSpinner = document.querySelector('#aside-contents-status');
  const [annual, setAnnual] = useState([]);
  const [page, setPage] = useState({ start: 0, end: 10 });
  const { start, end } = page;


  onLoad(async () => {
    const department = getDepartment();
    const response = await getAnnualtListByDepartment(department, page.start, page.end);
  
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
      if (scrollHeight - scrollTop === clinetHeight) {
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
    annualList.forEach((item) => {
      dom += `<li class="pb-3 sm:pb-4">
      <div class="flex items-center space-x-4">
        <div class="flex-shrink-0">
          <img class="w-8 h-8 rounded-full" src="/src/assets/images/user.svg" alt="Neil image">
        </div>
        <div class="flex-1 min-w-0">
            <p class="text-xs font-medium text-gray-900 truncate dark:text-white">
               ${item.name}
            </p>
            <p class="text-xs text-gray-500 truncate dark:text-gray-400">
              ${item.email} 
            </p>
         </div>
         <div class="inline-flex items-center text-xs font-semibold text-gray-900 dark:text-white">
         ${item.startDate} ~ ${item.endDate}
        </div>
      </div>
      </li>
      `
    });
    dom += '</ul>';
    return dom;
  }
  return annual;
}

