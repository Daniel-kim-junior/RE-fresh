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

async function getDepartmentNameList() {
  const response = await fetch(`/department/allList`);
  return await response.json();
}

async function getCalendarDataByDepartment(year, month, departmentName) {
  const response = await fetch(`/calendar/department?year=${year}&month=${month}&departmentName=${departmentName}`);
  return await response.json();
}


async function getAnnualListByMember(input, start, end) {
  const response = await fetch(`/annual/member?name=${input}&start=${start}&end=${end}`);
  return await response.json();
}

async function getAnnualtListByDepartment(input, start, end) {
  const response = await fetch(`/annual/department?name=${input}&start=${start}&end=${end}`);
  return await response.json();
}



export { getCalendarData, getAnnualListByMember, getAnnualtListByDepartment, getDepartmentNameList, getCalendarDataByDepartment };