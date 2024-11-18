const tham = document.querySelector(".tham");
const mobileMenu = document.getElementById("mobile-menu");

tham.addEventListener('click', () => {
    tham.classList.toggle('tham-active');
    mobileMenu.classList.toggle('hidden');
});