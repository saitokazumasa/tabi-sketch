// モーダルを閉じたときにinputからフォーカスを外す
document.querySelectorAll('[data-modal-hide]').forEach((closeButton) => {
    closeButton.addEventListener('click', () => {
        setTimeout(() => {
            document.activeElement.blur(); // 現在のアクティブ要素のフォーカスを外す
        }, 0);
    });
});

class PlaceNumManager {
    num = 1;
    increment() {
        this.num ++;
    }
}

const placeNum = new PlaceNumManager();

// 初回のフォームイベント登録
initializeForm();

async function getFragment() {
    try {
        const response = await fetch(`/fragment/modal/places?num=${placeNum.num+1}`);
        if (!response.ok) throw new Error('フラグメントの取得に失敗しました');
        return response.text();
    } catch (error) {
        console.error(error);
        return '';
    }
}

function addFragment(fragment) {
    const container = document.getElementById('destination');
    const item = document.createElement('div');
    item.innerHTML = fragment;
    container.appendChild(item);
}

function closeModal() {
    const modalElement = document.getElementById(`placeModal${placeNum.num}`);
    if (modalElement) {
        const modal = new Modal(modalElement);
        modal.hide();
    }
}

function initializeModal() {
    const modalElement = document.getElementById(`placeModal${placeNum.num}`);
    const toggleButton = document.getElementById(`placeToggleBtn${placeNum.num}`);
    const closeButton = document.getElementById(`placeClose${placeNum.num}`);

    if (modalElement && toggleButton && closeButton) {
        const modal = new Modal(modalElement);

        // トグルボタンでモーダルを開閉
        toggleButton.addEventListener('click', () => modal.toggle());
        closeButton.addEventListener('click', () => modal.hide());
    }
}

function initializeForm() {
    const form = document.getElementById(`placeForm${placeNum.num}`);
    if (!form) return;

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        // 次のフラグメントを取得して追加
        const fragment = await getFragment();
        if (fragment) {
            addFragment(fragment);
            closeModal();
            if (placeNum.num === 1) {
                initializeModal();
                console.log("1 init");
            }
            placeNum.increment();
            initializeModal();
            initializeForm();
        }
    });
}