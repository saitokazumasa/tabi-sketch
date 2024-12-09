export {openPopup, closePopup};

class MapManager {
    constructor(options = {}) {
        this.defaultLocation = options.defaultLocation || {
            lat: 35.675682101601765,
            lng: 139.66842469787593
        };
        this.defaultZoom = options.defaultZoom || 12;
        this.map = null;
        this.popup = document.getElementById('popup');
    }

    /**
     * 指定された要素IDに地図を表示する
     * @param {string} elementId - 地図を表示する要素のID
     */
    displayMap(elementId) {
        const mapElement = document.getElementById(elementId);
        if (!mapElement) return;

        this.map = new google.maps.Map(mapElement, {
            center: this.defaultLocation,
            zoom: this.defaultZoom
        });
    }

    /**
     * ポップアップを開く
     */
    openPopup() {
        if (this.popup) {
            this.popup.style.display = 'flex';
            this.displayMap('sp-map');
        }
    }

    /**
     * ポップアップを閉じる
     */
    closePopup() {
        if (this.popup) {
            this.popup.style.display = 'none';
        }
    }

    /**
     * 初期地図を表示
     */
    initMap() {
        this.displayMap('map');
    }
}

// グローバルインスタンスの作成
const mapManager = new MapManager();

// イベントリスナーの設定
window.addEventListener('load', () => mapManager.initMap());

// グローバル関数の定義（互換性のため）
function openPopup() {
    mapManager.openPopup();
}

function closePopup() {
    mapManager.closePopup();
}
