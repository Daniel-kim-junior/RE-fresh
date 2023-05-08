

function cancelAnnual(uid,status,start,end,type) {
    console.log(status);

    let list = end.match(/\d+/g);
    endDate = new Date(list[0]+'-'+list[1]+'-'+list[2]);
    list=start.match(/\d+/g);
    startDate = new Date(list[0]+'-'+list[1]+'-'+list[2]);
    console.log(endDate);
    fetch("http://localhost:8090/mypage/cancel",{
        method: "put",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            uid :  uid,
            status : status,
            startDate : startDate,
            endDate : endDate,
            annualType: type,
        })
    }).then(res =>{
        if(res.ok){
            alert("정상적으로 취소 되었습니다.");
            window.location.replace("http://localhost:8090/mypage/history");
        }else{
            alert("요청하신 처리를 실패했습니다. 다시 시도해 주세요.");
        }
    })

}