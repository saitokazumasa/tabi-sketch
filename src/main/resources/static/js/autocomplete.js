class SessionTokenManager {
    sessionToken = new google.maps.places.AutocompleteSessionToken();
    regenerateSessionToken() {
        this.sessionToken = new google.maps.places.AutocompleteSessionToken();
    }
}

let sessionManager;

function InitAutocomplete() {
    sessionManager = new SessionTokenManager();

    const initialInstance = [
        'startPlace',
        'endPlace',
        'place1'
    ];

    initialInstance.forEach((instanceId) => {
        const inputElement = document.getElementById(instanceId);
        if (inputElement) {
            setupAutocomplete(inputElement);
        }
    })
}

function setupAutocomplete(element) {
    const autocomplete = new google.maps.places.Autocomplete(element, {
        types: ['establishment'],
        componentRestrictions: { country: ['JP'] },
        fields: ['place_id', 'geometry', 'name'],
    });

    autocomplete.setOptions({ sessionToken: sessionManager.sessionToken });
    autocomplete.addListener('place_changed', () => onPlaceChanged(element, autocomplete));
}

function onPlaceChanged(element, autocomplete) {
    const place = autocomplete.getPlace();

    if (!place.geometry) {
        element.placeholder = '場所を入力して下さい';
    } else {
        element.value = place.name;

        sessionManager.regenerateSessionToken()
        autocomplete.setOptions({ sessionToken: sessionManager.sessionToken });
    }
}