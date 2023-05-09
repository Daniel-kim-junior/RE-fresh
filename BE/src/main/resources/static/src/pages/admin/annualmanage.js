
 const showEditAnnual = () => {
    const modal = document.getElementById('modal');
    console.log("!!");
     modal.classList.remove('hidden');
     modal.classList.add('flex');
}

const closeEditAnnual = () => {
    const modal=document.getElementById('modal');
    modal.classList.add('hidden');
    modal.classList.remove('flex');
}



