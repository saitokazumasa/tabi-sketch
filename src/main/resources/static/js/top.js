const images = [
    '/images/top1.png',
    '/images/top2.png',
    '/images/top3.png',
    '/images/top4.png',
    '/images/top5.png',
    '/images/top6.png',
];
let currentIndex = 0;
const imagesElement = document.getElementById('images');

setInterval(() => {
    // 次の画像に切り替える
    currentIndex = (currentIndex + 1) % images.length;
    imagesElement.src = images[currentIndex];
}, 10000); // 10秒ごとに切り替え