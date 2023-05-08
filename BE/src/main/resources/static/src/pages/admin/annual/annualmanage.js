let annualuid;
let endDate;
let startDate;
let annualType;
let deptName;

/**
    Park JuHee
    관리자가 연차 신청 승인여부를 변경할 modal을 보여주는 함수
    v2. 2023-05-06
    */
 const showEditAnnual = (uid, status, start, end, type, departmentName) => {
     let list = end.match(/\d+/g);
     endDate = new Date(list[0]+'-'+list[1]+'-'+list[2]);
     if(status == '승인' || endDate < new Date()) return;
     console.log(departmentName);
     list=start.match(/\d+/g);
     startDate = new Date(list[0]+'-'+list[1]+'-'+list[2]);
     annualType = type;

     const modal = document.getElementById('modal');
     deptName = departmentName;
     modal.classList.remove('hidden');
     annualuid = uid;
     modal.classList.add('flex');
}

/**
 * Park JuHee
 * 관리자가 연차 신청 승인여부를 변경할 modal을 닫는 함수 
 */
const closeEditAnnual = () => {
    const modal=document.getElementById('modal');
    modal.classList.add('hidden');
    modal.classList.remove('flex');
}

/**
 * Park JuHee
 * 연차 승인,반려를 update 기능을 처리하는 함수
 * 2023-05-04
 * */
function updateStatus() {
    const radioBnt = document.getElementsByName('approved');
    if(!radioBnt[0].checked &&!radioBnt[1].checked ) {
        alert("옵션을 선택해 주세요.");
    }else{
        const selectedOption = radioBnt[0].checked ? '승인' : '반려'; // 승인 버튼을 눌렀을 때 true

        fetch("http://localhost:8090/admin/status", {
            method: "put",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                uid: annualuid,
                endDate: endDate,
                startDate: startDate,
                annualType : annualType,
                status: selectedOption,
                departmentName: deptName
            })
        }).then(res => {
            if(res) {
                closeEditAnnual();
                window.location.replace("http://localhost:8090/admin/annualManage?page=1");
            }else{
                alert("요청한 처리를 실패했습니다. 다시 시도해 주세요.")
            }
        })
    }

}



