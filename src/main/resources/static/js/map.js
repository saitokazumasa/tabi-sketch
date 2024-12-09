class MapManager {
    constructor(options = {}) {
        this.defaultLocation = options.defaultLocation || {
            // TODO:適当な初期値を指定しているため、開始地点>目的地点>終了地点の優先度でPlaceの緯度経度を取得するように修正する
            lat: 35.675682101601765,
            lng: 139.66842469787593
        };
        this.defaultZoom = options.defaultZoom || 12;
        this.map = null;
        this.popup = document.getElementById('popup');
    }

    displayMap(elementId) {
        const mapElement = document.getElementById(elementId);
        if (!mapElement) return;

        this.map = new google.maps.Map(mapElement, {
            center: this.defaultLocation,
            zoom: this.defaultZoom
        });
    }
    openPopup() {
        if (this.popup) {
            this.popup.style.display = 'flex';
            this.displayMap('sp-map');
        }
    }

    closePopup() {
        if (this.popup) {
            this.popup.style.display = 'none';
        }
    }
    initMap() {
        this.displayMap('map');
    }
}

// グローバルインスタンスの作成
const mapManager = new MapManager();

// イベントリスナーの設定
window.onload = () => mapManager.initMap();

function openPopup() {
    mapManager.openPopup();
}

function closePopup() {
    mapManager.closePopup();
}