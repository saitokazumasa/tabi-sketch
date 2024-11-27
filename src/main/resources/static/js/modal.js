// モーダルを閉じたときにinputからフォーカスを外す
document.querySelectorAll('[data-modal-hide]').forEach((closeButton) => {
    closeButton.addEventListener('click', () => {
        setTimeout(() => {
            document.activeElement.blur(); // 現在のアクティブ要素のフォーカスを外す
        }, 0);
    });
});