export {openPopup, closePopup};

class Map {
    constructor(options = {}) {
        this.defaultLocation = options.defaultLocation || {
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

const map = new Map();

window.addEventListener('load', () => map.initMap());

function openPopup() {
    map.openPopup();
}

function closePopup() {
    map.closePopup();
}
