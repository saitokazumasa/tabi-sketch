class MapDisplayController {
    constructor() {
        this.defaultCenter = { lat: 35.675682101601765, lng: 139.66842469787593 };
        this.defaultZoom = 12;

        if (typeof google === 'undefined') {
            console.error('Google Maps APIが読み込まれていません。');
            return;
        }

        this.directionsService = new google.maps.DirectionsService();
        this.directionsRenderer = new google.maps.DirectionsRenderer();

        this.initEventListeners();
    }

    displayMap(elementId, options = {}) {
        const mapOptions = {
            center: options.center || this.defaultCenter,
            zoom: options.zoom || this.defaultZoom,
        };

        const mapElement = document.getElementById(elementId);
        if (mapElement) {
            const map = new google.maps.Map(mapElement, mapOptions);
            this.directionsRenderer.setMap(map);
            return map;
        } else {
            console.error(`Element with ID "${elementId}" not found.`);
        }
    }

    addInvisibleMarker(position) {
        const marker = new google.maps.Marker({
            position: position,
            map: this.directionsRenderer.getMap(),
            visible: false // マーカーを非表示に設定
        });
    }

    async displayDirectionsByPlaceIds(placeIds, travelModes, options) {
        if (placeIds.length < 2) {
            console.error('少なくとも2つのplaceIdを指定してください。');
            return;
        }

        if (travelModes.length !== placeIds.length - 1) {
            console.error('travelModesの長さはplaceIdsの長さ-1でなければなりません。');
            return;
        }

        const directionsRendererArray = [];

        for (let i = 0; i < placeIds.length - 1; i++) {
            const request = {
                origin: { placeId: placeIds[i] },
                destination: { placeId: placeIds[i + 1] },
                travelMode: travelModes[i],
                drivingOptions: {
                    departureTime: new Date(Date.now()),
                    trafficModel: 'pessimistic'
                },
                avoidHighways: options.useHighway ? false : true,
                avoidFerries: options.useFerry ? false : true
            };

            await this.directionsService.route(request, (result, status) => {
                if (status === 'OK') {
                    const renderer = new google.maps.DirectionsRenderer();
                    renderer.setMap(this.directionsRenderer.getMap());
                    renderer.setDirections(result);
                    directionsRendererArray.push(renderer);

                    // 経路の始点と終点に非表示のマーカーを追加
                    this.addInvisibleMarker(result.routes[0].legs[0].start_location);
                    this.addInvisibleMarker(result.routes[0].legs[0].end_location);
                } else {
                    console.error('Directions request failed due to ' + status);
                }
            });
        }

        this.directionsRendererArray = directionsRendererArray;
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

    initMap(placeIds, travelModes, options) {
        const map = this.displayMap('map');
        if (placeIds && placeIds.length > 0 && travelModes && travelModes.length > 0) {
            this.displayDirectionsByPlaceIds(placeIds, travelModes, options);
        } else {
            this.displayDirections({ lat: 35.681236, lng: 139.767125 }, { lat: 35.689487, lng: 139.691706 });
        }
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

function initializeMaps(placeIds = [], travelModes = [], options = {}) {
    const initializeMap = new MapDisplayController();
    initializeMap.initMap(placeIds, travelModes, options);
}

window.initializeMaps = initializeMaps;
window.openPopup = function() {
    const initializeMap = new MapDisplayController();
    if (typeof google !== 'undefined') {
        initializeMap.openPopup();
    }
};
window.closePopup = function() {
    const initializeMap = new MapDisplayController();
    if (typeof google !== 'undefined') {
        initializeMap.closePopup();
    }
};
