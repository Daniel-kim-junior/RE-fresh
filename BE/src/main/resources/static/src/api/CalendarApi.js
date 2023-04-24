/*
    Daniel Kim
    
    테이블에 넣을 달력 데이터를 서버에게 요청하는 메소드
    fetch 함수를 사용하여 서버에 요청을 보낸다.
    요청을 보낸 후 json 형태로 변환하여 반환한다.
    
    2023-04-16
*/
async function getCalendarData(year, month) { 
  const response = await fetch(`/calendar?year=${year}&month=${month}`);
  return await response.json();
}

export default getCalendarData;