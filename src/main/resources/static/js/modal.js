class PlaceNum {
    #value = 1;

    value() {
        return this.#value;
    }

    increment() {
        this.#value ++;
    }
}

class SessionStorageList {
    #startPlaceList = new Array(0);
    #endPlaceList = new Array(0);
    #placesList = new Array(0);

    setStartPlace() {
        this.#startPlaceList = [];
        this.#startPlaceList.push({
            placeId: document.getElementById('startPlaceId').value,
            lat: document.getElementById('startLat').value,
            lng: document.getElementById('startLng').value,
            name: document.getElementById('startPlace').value,
            startTime: document.getElementById('startTime').value
        });

        sessionStorage.setItem('startPlace', JSON.stringify(this.#startPlaceList));
    }

    setEndPlace() {
        this.#endPlaceList = [];
        this.#endPlaceList.push({
            placeId: document.getElementById('endPlaceId').value,
            lat: document.getElementById('endLat').value,
            lng: document.getElementById('endLng').value,
            name: document.getElementById('endPlace').value
        });

        sessionStorage.setItem('endPlace', JSON.stringify(this.#endPlaceList));
    }

    setPlaces() {
        this.#placesList.push({
            placeId: document.getElementById(`placeId${placeNum}`).value,
            lat: document.getElementById(`placeLat${placeNum}`).value,
            lng: document.getElementById(`placeLng${placeNum}`).value,
            name: document.getElementById(`place${placeNum}`).value,
            budget: document.getElementById(`budget${placeNum}`).value,
            stayTime: document.getElementById(`stayTime${placeNum}`).value,
            desiredStartTime: document.getElementById(`desiredStartTime${placeNum}`).value,
            desiredEndTime: document.getElementById(`desiredEndTime${placeNum}`).value,
        });

        sessionStorage.setItem('place', JSON.stringify(this.#placesList));
    }
}

class Fragment {
    #value;

    constructor() {
        this.#value = null;
    }

    async initialize() {
        try {
            const response = await fetch(`/fragment/modal/places?num=${(placeNum.value()+1)}`);
            if (!response.ok) { throw new Error('フラグメントの取得に失敗しました'); }
            this.#value = await response.text();
        } catch (error) {
            throw new Error('initialize Error : ' + error);
        }
    }

    addFragment() {
        if (this.#value === null) throw new Error('このインスタンスは初期化されていません。initialize()を実行してください。');
        // id=destination の子要素に追加
        const container = document.getElementById('destination');
        const item = document.createElement('div');
        item.innerHTML = this.#value;
        container.appendChild(item);
    }

    value() {
        if (this.#value === null) throw new Error('このインスタンスは初期化されていません。initialize()を実行してください。');
        return this.#value;
    }
}

class ModalElement {
    #modal;

    constructor() {
        const modalElement = document.getElementById(`placeModal${placeNum.value()}`);
        const toggleBtn = document.getElementById(`placeToggleBtn${placeNum.value()}`);
        const closeBtn = document.getElementById(`placeClose${placeNum.value()}`);
        const inputElement = document.getElementById(`place${placeNum.value()}`);

        if (!modalElement || !toggleBtn || !closeBtn || !inputElement) { return; }
        this.#modal = new Modal(modalElement);

        toggleBtn.addEventListener('click', () => this.#modal.toggle());
        closeBtn.addEventListener('click', () => {
            this.#modal.hide();
            document.activeElement.blur();
        });
        // eslint-disable-next-line no-undef
        const autoComplete = new AutoComplete(inputElement);
        // eslint-disable-next-line no-undef
        autoCompleteList.add(autoComplete);
    }

    closeModal() {
        this.#modal.hide();
    }
}

class ModalForm {
    #startFormElement;
    #placeFormElement;
    #endFormElement;

    constructor() {
        this.#startFormElement = document.getElementById('startPlaceForm');
        this.#placeFormElement = document.getElementById(`placeForm${placeNum.value()}`);
        this.#endFormElement = document.getElementById('endPlaceForm');
        this.initFormEvent();
    }

    initFormEvent() {
        if (!this.#startFormElement || !this.#placeFormElement || !this.#endFormElement) return;
        this.#startFormElement.addEventListener('submit', async(e) => {
            e.preventDefault();

            sessionStorageList.setStartPlace();

            const modal = new Modal(document.getElementById('startModal'));
            modal.hide();
        });
        this.#endFormElement.addEventListener('submit', async(e) => {
            e.preventDefault();

            sessionStorageList.setEndPlace();

            const modal = new Modal(document.getElementById('endModal'));
            modal.hide();
        });
        this.addPlaceEvent();
    }

    addPlaceEvent() {
        this.#placeFormElement.addEventListener('submit', async(e) => {
            e.preventDefault();

            sessionStorageList.setPlaces();

            // 追加するフラグメントの呼び出し
            const fragment = new Fragment();
            await fragment.initialize();
            if (!fragment.value()) return;

            const modal = new ModalElement();

            fragment.addFragment();
            modal.closeModal();

            // 追加したフラグメントに対して実行
            placeNum.increment();
            new ModalElement();
            new ModalForm();
        });
    }
}

const placeNum = new PlaceNum();
const sessionStorageList = new SessionStorageList();

new ModalForm();
