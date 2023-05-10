import { useState, onLoad, getName, waitForRender, optionChecked } from './hook.js';
import { getAnnualListByMember } from '../../../../api/calendarApi.js'

let scrollFlag = false;
let scrollEnd = false;

export function memberScrollInit() {
  scrollEnd = false;
}

export default function AsideMember() {
  const [annual, setAnnual] = useState([]);
  const [page, setPage] = useState({start: 0, end: 10});
  const { start, end } = page;
  const asideContents = document.querySelector('#aside-contents');
  const loadingSpinner = document.querySelector('#aside-contents-status');
  const checked = optionChecked();
  const date = new Date();

  onLoad(async () => {
    if (scrollEnd) return;
    const memberName = getName();
    const dom = await recursiveList('', memberName, start, end, 0);
    const list = parseDom(dom);

    if (start === 0) {
      setAnnual(list);
    } else if(scrollFlag) {
      setAnnual(annual.concat(list));
      scrollFlag = false;
    }
  });
  function parseDom(dom) {
    let result = '<ul class="max-w-md divide-y divide-gray-200 dark:divide-gray-700">'
    result += dom;
    result += '</ul>';

    return result;
  }



  async function recursiveList(str, member, start, end, innerCount) {
    let res = await getAnnualListByMember(member, start, end);
   
    if (res.length === 0) {
      scrollEnd = true; 
      return str;
    }

    let [dom, cnt] = makeAnnualList(res);
    if (cnt === 0) return str;

    if (cnt + innerCount >= 10) {
      return str.concat(dom);
    }
    return recursiveList(str.concat(dom), member, start + 10, 10, cnt + innerCount);
  }





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
    let dom = '';
    let cnt = '';
    for (let i = 0; i < annualList.length; i++) {
      if (checked) {
        if (Date.parse(annualList[i].endDate) < Date.parse(date)) {
          continue;
        }
      }
      cnt++;
      dom += `<li class="pb-3 sm:pb-4">
      <div class="flex items-center space-x-4">
        <div class="flex-shrink-0">
          <img class="w-8 h-8 rounded-full" src="/src/assets/images/user.svg" alt="Neil image">
        </div>
        <div class="flex-1">
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
    return [dom, cnt];
  }
  return annual;
}

