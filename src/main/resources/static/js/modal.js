class PlaceNum {
    #value;

    constructor() {
        this.#value = 1;
    }

    value() {
        return this.#value;
    }

    increment() {
        this.#value ++;
    }
}

class SessionStorageList {
    #places;
    #budget;
    #stayTime;
    #desiredStartTime;
    #desiredEndTime;

    constructor() {
        this.#places = [];
        this.#budget = [];
        this.#stayTime = [];
        this.#desiredStartTime = [];
        this.#desiredEndTime = [];
    }

    pushList() {
        this.#places.push(document.getElementById(`place${placeNum.value()}`).value);
        this.#budget.push(document.getElementById(`budget${placeNum.value()}`).value);
        this.#stayTime.push(document.getElementById(`stayTime${placeNum.value()}`).value);
        this.#desiredStartTime.push(document.getElementById(`desiredStartTime${placeNum.value()}`).value);
        this.#desiredEndTime.push(document.getElementById(`desiredEndTime${placeNum.value()}`).value);
    }

    addSessionStorage() {
        sessionStorage.setItem('places', JSON.stringify(this.#places));
        sessionStorage.setItem('placesBudget', JSON.stringify(this.#budget));
        sessionStorage.setItem('placesStayTime', JSON.stringify(this.#stayTime));
        sessionStorage.setItem('placesDesiredStartTime', JSON.stringify(this.#desiredStartTime));
        sessionStorage.setItem('placesDesiredEndTime', JSON.stringify(this.#desiredEndTime));
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
            console.error(error);
            return '';
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

            sessionStorage.setItem('startPlace', document.getElementById('startPlace').value);
            sessionStorage.setItem('startTime', document.getElementById('startTime').value);

            const modal = new ModalElement();
            modal.closeModal();
        });
        this.#endFormElement.addEventListener('submit', async(e) => {
            e.preventDefault();

            sessionStorage.setItem('endPlace', document.getElementById('endPlace').value);

            const modal = new ModalElement();
            modal.closeModal();
        });
        this.addPlaceEvent();
    }

    addPlaceEvent() {
        this.#placeFormElement.addEventListener('submit', async(e) => {
            e.preventDefault();

            sessionStorageList.pushList();
            sessionStorageList.addSessionStorage();

            // 追加するフラグメントの呼び出し
            const fragment = new Fragment();
            await fragment.initialize();
            if (!fragment.value()) return;

            const modal = new ModalElement();

            fragment.addFragment();
            modal.closeModal();

            placeNum.increment();
            new ModalElement();
            new ModalForm();
        });
    }
}

const placeNum = new PlaceNum();
const sessionStorageList = new SessionStorageList();

new ModalForm();
