let autocompleteInstances = {}; // 各インスタンスの保持
let sessionToken;

// Autocompleteの初期設定
function InitAutocomplete() {
    sessionToken = new google.maps.places.AutocompleteSessionToken();

    // startPlaceとendPlaceに対するAutocompleteの設定
    SetupAutocomplete('startPlace');
    SetupAutocomplete('endPlace');

    const placeInputs = document.querySelectorAll("[id^='place']");
    placeInputs.forEach((input) => {
        SetupAutocomplete(input.id);
    });
}

// Autocompleteを使う要素の適用
function SetupAutocomplete(elementId) {
    const inputElement = document.getElementById(elementId);
    if (!inputElement) {
        console.error(`Element with ID ${elementId} not found`);
        return;
    }

    const autocomplete = new google.maps.places.Autocomplete(inputElement, {
        types: ['establishment'],
        componentRestrictions: { country: ['JP'] },
        fields: ['place_id', 'geometry', 'name'],
    });

    autocomplete.setOptions({ sessionToken: sessionToken });
    autocomplete.addListener('place_changed', () => OnPlaceChanged(elementId, autocomplete));

    // Autocomplete インスタンスを保持
    autocompleteInstances[elementId] = autocomplete;
}

// 場所検索の入力時
function OnPlaceChanged(elementId, autocomplete) {
    let place = autocomplete.getPlace();
    const inputElement = document.getElementById(elementId);

    if (!place.geometry) {
        inputElement.placeholder = '場所を入力して下さい';
    } else {
        inputElement.value = place.name;

        /* 各情報の取得
        console.log(`ID: ${elementId}`);
        console.log(`Name: ${place.name}`);
        console.log(`Place ID: ${place.place_id}`);
        console.log(`Latitude: ${place.geometry.location.lat()}`);
        console.log(`Longitude: ${place.geometry.location.lng()}`);
        */

        // セッショントークン再生成
        sessionToken = new google.maps.places.AutocompleteSessionToken();
        autocomplete.setOptions({ sessionToken: sessionToken });
    }
}