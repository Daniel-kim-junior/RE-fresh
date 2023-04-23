/*
    Daniel Kim
    
    테이블에 넣을 달력 데이터를 서버에게 요청하는 메소드
    초기 렌더링은 현재 날짜의 달력을 보여주게 구현할 예정
    연도와 달을 파라미터로 받아서 해당 연도와 달의 달력을 보여주게 구현할 예정
    
    2023-04-16
*/
async function getCalendarData(year, month) { 
  const response = await fetch(`/calendar?year=${year}&month=${month}`);
  return await response.json();
}

export default getCalendarData;