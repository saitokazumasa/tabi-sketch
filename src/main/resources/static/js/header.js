const tham = document.querySelector(".tham");
const mobileMenu = document.getElementById("mobile-menu");

tham.addEventListener('click', () => {
    tham.classList.toggle('tham-active');
    mobileMenu.classList.toggle('hidden');

    // ウィンドウのリサイズイベント
    window.addEventListener('resize', () => {
        mobileMenu.classList.add('hidden');  // メニューを非表示にする
        tham.classList.remove('tham-active'); // ハンバーガーアイコンを元の状態に戻す
    });
});
