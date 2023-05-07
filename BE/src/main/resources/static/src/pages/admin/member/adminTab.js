var memberBtn = document.getElementById("memberBtn");
var memberNewBtn = document.getElementById("memberNewBtn");
var annualManageBtn = document.getElementById("annualManageBtn");

memberBtn.onclick = button1;
memberNewBtn.onclick = button2;
annualManageBtn.onclick = button3;

window.onload = function () {
    if(window.location.pathname == "/admin/members" || window.location.pathname == "/admin/members/search") {
        memberBtn.setAttribute('class', 'p-4 font-bold text-blue-500 border-b-2 border-transparent round-t-lg border-blue-500')
        memberNewBtn.setAttribute('class', 'p-4 border-b-2 border-transparent round-t-lg hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300')
        annualManageBtn.setAttribute('class', 'p-4 border-b-2 border-transparent round-t-lg hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300')
    } else if(window.location.pathname == "/admin/members/new") {
        memberBtn.setAttribute('class', 'p-4 border-b-2 border-transparent round-t-lg hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300')
        memberNewBtn.setAttribute('class', 'p-4 font-bold text-blue-500 border-b-2 border-transparent round-t-lg border-blue-500')
        annualManageBtn.setAttribute('class', 'p-4 border-b-2 border-transparent round-t-lg hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300')
    } else if(window.location.pathname == "/admin/annualManage" || window.location.pathname == "/admin/annualManage/search"){
        memberBtn.setAttribute('class', 'p-4 border-b-2 border-transparent round-t-lg hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300')
        memberNewBtn.setAttribute('class', 'p-4 border-b-2 border-transparent round-t-lg hover:text-gray-600 hover:border-gray-300 dark:hover:text-gray-300')
        annualManageBtn.setAttribute('class', 'p-4 font-bold text-blue-500 border-b-2 border-transparent round-t-lg border-blue-500')
    }
}

function button1() {
    window.location.href = "/admin/members?page=1";
}

function button2() {
    window.location.href = "/admin/members/new";
}

function button3() {
    window.location.href = "/admin/annualManage?page=1";
}
