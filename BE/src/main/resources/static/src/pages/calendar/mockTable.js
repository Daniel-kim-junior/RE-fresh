

export default function Mock() {
  const mockTable = document.getElementById("mock-table");
  const spinner = document.getElementById("loading-spinner");
  const prevBtn = document.getElementById("prev-btn");
  const nextBtn = document.getElementById("next-btn");

  prevBtn.innerHTML = '&larr; Prev';
  nextBtn.innerHTML = 'Next &rarr;';
  prevBtn.disabled = false;
  nextBtn.disabled = false;

  mockTable.style.display = 'none';
  spinner.style.display = 'none';
}