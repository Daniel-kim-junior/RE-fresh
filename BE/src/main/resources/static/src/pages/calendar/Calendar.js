/*
    Daniel Kim
    
    Calendar.html에 해당하는 js파일
    
    2023-04-16
*/

const mainContainer = document.querySelector('#main-container');

/*
    Daniel Kim

    테이블에 넣을 달력 DOM을 만드는 메소드
    cnt를 증가시키면서 몫에 해당하는 행에 td를 넣어준다.(테이블 줄바꿈을 위해)
    
    2023-04-16
*/
const makeCalendar = (list, holList) => {
  let dom = '<tr>';
  // 달력 그리기
  let cnt = 0;
  for (let i = 0; i < list.length; i++) {
    if (parseInt(i / 7) === cnt) {
      dom += `<td class="pb-20 pl-20 border px-4 py-2">${list[i]}</td>`;
    } else {
      dom += `</tr><tr><td class="pb-20 pl-20 border px-4 py-2">${list[i]}</td>`;
      cnt++;
    }
  }
  dom += '</tr>'
  return dom;
}

/*
    Daniel Kim
    
    테이블에 넣을 달력 데이터를 서버에게 요청하는 메소드
    초기 렌더링은 현재 날짜의 달력을 보여주게 구현할 예정
    연도와 달을 파라미터로 받아서 해당 연도와 달의 달력을 보여주게 구현할 예정
    
    2023-04-16
*/
async function getCalendarData(year, month) {
  let response;
  if (year === undefined || month === undefined) {
    response = await fetch(`/calendar`);
  } else {
    response = await fetch(`/calendar?year=${year}&month=${month}`);
  }
  return await response.json();
}


/*
    Daniel Kim  
    
    전체 메소드들을 호출하여 달력을 렌더링하는 메소드
    현재는 초기 렌더링을 위해 2023년 4월의 달력을 보여주게 구현
    후에는 파라미터로 연도와 달을 받아서 해당 연도와 달의 달력을 보여주게 구현할 예정

    2023-04-16
*/
const makeCalDom = async () => {
  const rst = await getCalendarData(2023, 4);
  const calIdxList = rst.calRst;
  const holList = rst.holRst;
  const dom = makeCalendar(calIdxList, holList);
  mainContainer.innerHTML = dom;
}

export default makeCalDom;

