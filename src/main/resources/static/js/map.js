class MapDisplayController {
    constructor() {
        this.defaultCenter = { lat: 35.675682101601765, lng: 139.66842469787593 };
        this.defaultZoom = 12;

        this.initEventListeners();
    }

    displayMap(elementId, options = {}) {
        const mapOptions = {
            center: options.center || this.defaultCenter,
            zoom: options.zoom || this.defaultZoom,
        };

        const mapElement = document.getElementById(elementId);
        if (mapElement) {
            return new google.maps.Map(mapElement, mapOptions);
        } else {
            console.error(`Element with ID "${elementId}" not found.`);
        }
    }

    openPopup() {
        const popup = document.getElementById('popup');
        if (popup) {
            popup.style.display = 'flex';
            this.displayMap('sp-map');
        }
    }

    closePopup() {
        const popup = document.getElementById('popup');
        if (popup) {
            popup.style.display = 'none';
        }
    }

    initMap() {
        this.displayMap('map');
    }

    initEventListeners() {
        const popupButton = document.querySelector('button[onclick="openPopup()"]');
        if (popupButton) {
            popupButton.addEventListener('click', (e) => {
                e.preventDefault();
                this.openPopup();
            });
        }

        const popup = document.getElementById('popup');
        if (popup) {
            popup.addEventListener('click', (e) => {
                if (e.target === popup) {
                    this.closePopup();
                }
            });
        }
    }
}

function initializeMaps() {
    const initializeMap = new MapDisplayController();
    initializeMap.initMap();
}

window.initializeMaps = initializeMaps;
window.openPopup = new MapDisplayController().openPopup.bind(new MapDisplayController());
window.closePopup = new MapDisplayController().closePopup.bind(new MapDisplayController());

