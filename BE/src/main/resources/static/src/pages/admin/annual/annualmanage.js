   /*
    Park JuHee
    관리자가 연차 신청 승인여부를 변경할 modal을 보여주는 함수  
    2023-05-01
    */
   let annualuid;
 const showEditAnnual = (uid) => {
     const modal = document.getElementById('modal');
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

function updateStatus() {
    const radioBnt = document.getElementsByName('approved');
    if(!radioBnt[0].checked &&!radioBnt[1].checked ) {
        alert("옵션을 선택해 주세요.");
    }else{
        const selectedOption = radioBnt[0].checked;
        console.log(selectedOption);

        fetch("http://localhost:8090/admin/status", {
            method: "put",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                uid: annualuid,
                status: selectedOption,
            })
        }).then(res => {
            if(res){
                closeEditAnnual();
                window.location.replace("http://localhost:8090/admin/annualManage");
            }else{
                alert("요청한 처리를 실패했습니다. 다시 시도해 주세요.")
            }
        })
    }

}



