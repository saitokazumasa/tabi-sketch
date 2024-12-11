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

    setPlaces(num) {
        this.#placesList[num-1] = {
            placeId: document.getElementById(`placeId${num}`).value,
            lat: document.getElementById(`placeLat${num}`).value,
            lng: document.getElementById(`placeLng${num}`).value,
            name: document.getElementById(`place${num}`).value,
            budget: document.getElementById(`budget${num}`).value,
            stayTime: document.getElementById(`stayTime${num}`).value,
            desiredStartTime: document.getElementById(`desiredStartTime${num}`).value,
            desiredEndTime: document.getElementById(`desiredEndTime${num}`).value,
        };

        sessionStorage.setItem('place', JSON.stringify(this.#placesList));
    }

    getStartData() {
        const startPlaceData = sessionStorage.getItem('startPlace');

        if (!startPlaceData) return;
        const startPlaceList = JSON.parse(startPlaceData);
        return startPlaceList[0];
    }

    getEndData() {
        const endPlaceData = sessionStorage.getItem('endPlace');

        if (!endPlaceData) return;
        const endPlaceList = JSON.parse(endPlaceData);
        return endPlaceList[0];
    }

    getPlacesData(num) {
        const placeData = sessionStorage.getItem('place');

        if (!placeData) return;
        const placeList = JSON.parse(placeData);
        return placeList[num];
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
    #modals;
    #toggleButtons;
    #closeButtons;

    constructor() {
        this.#modals = {
            start: document.getElementById('startModal'),
            end: document.getElementById('endModal'),
            places: [document.getElementById(`placeModal${placeNum.value()}`)],
        };

        this.#toggleButtons = {
            start: document.getElementById('startToggle'),
            end: document.getElementById('endToggle'),
            places: [document.getElementById(`placeToggleBtn${placeNum.value()}`)],
        };

        this.#closeButtons = {
            start: document.getElementById('startClose'),
            end: document.getElementById('endClose'),
            places: [document.getElementById(`placeCloseBtn${placeNum.value()}`)],
        };
    }

    addPlacesElement() {
        const modal = document.getElementById(`placeModal${placeNum.value()}`);
        const toggleButton = document.getElementById(`placeToggleBtn${placeNum.value()}`);
        const closeButton = document.getElementById(`placeCloseBtn${placeNum.value()}`);

        this.#modals.places.push(modal);
        this.#toggleButtons.places.push(toggleButton);
        this.#closeButtons.places.push(closeButton);
        this.addButtonEvent('places');

        const inputElement = document.getElementById(`place${placeNum.value()}`);
        const autoComplete = new AutoComplete(inputElement);
        autoCompleteList.add(autoComplete);
    }

    /**
     * ModalButtonイベント アタッチ
     * @param modalType (start,end,places)が入る
     */
    addButtonEvent(modalType) {
        const modal = this.#getModal(modalType ,placeNum.value()-1);
        const toggleBtn = this.#getToggleBtn(modalType);
        const closeBtn = this.#getCloseBtn(modalType);

        // イベントのアタッチ
        toggleBtn.addEventListener('click', () => modal.toggle() );
        closeBtn.addEventListener('click', () => {
            modal.hide();
            document.activeElement.blur(); // フォーカスを外す
        });
    }

    #getModal(modalType, num) {
        if (modalType === 'places') {
            return new Modal(this.#modals[modalType][num]);
        }
        return new Modal(this.#modals[modalType]);
    }

    #getToggleBtn(modalType) {
        if (modalType === 'places') {
            return this.#toggleButtons.places[placeNum.value()-1];
        }
        return this.#toggleButtons[modalType];
    }

    #getCloseBtn(modalType) {
        if (modalType === 'places') {
            return this.#closeButtons.places[placeNum.value()-1];
        }
        return this.#closeButtons[modalType];
    }

    closeModal(modalType, num) {
        const modal = this.#getModal(modalType, num);
        modal.hide();
    }

    changeStartDisplay() {
        const spans = document.querySelectorAll('#startToggle span'); // spanタグ取得

        const data = sessionStorageList.getStartData();
        spans[0].textContent = data.startTime; // 開始時間を入れる
        spans[1].textContent = data.name; // spanの文字を場所名に
        spans[0].classList.remove('absolute');
    }

    changeEndDisplay() {
        const span = document.querySelector('#endToggle span'); // spanタグ取得

        span.textContent = sessionStorageList.getEndData().name; // spanの文字を場所名に
    }

    changePlaceDisplay(num) {
        const spans = document.querySelectorAll(`#placeToggleBtn${num} > span`); // buttonの子要素のspanタグ取得

        const data = sessionStorageList.getPlacesData(num-1); // sessionの値を取得

        // 場所名
        spans[1].textContent = data.name;

        // 希望時間
        if (!data.desiredStartTime) spans[0].textContent = '';
        else spans[0].textContent = data.desiredStartTime + '~' + data.desiredEndTime;
        spans[0].classList.remove('absolute');

        // 予算
        const budget = document.getElementById(`budgetDisplay${num}`);
        if (!data.budget) budget.textContent = '予算：----' + '円';
        else budget.textContent = '予算：' + data.budget + '円';

        // 滞在時間
        const stayTime = document.getElementById(`stayTimeDisplay${num}`);
        if (!data.stayTime) stayTime.textContent = '滞在時間：30分';
        else stayTime.textContent = '滞在時間：' + data.stayTime + '分';

        // 緑色の枠線をけす
        const toggleBtn = document.getElementById(`placeToggleBtn${num}`);
        toggleBtn.classList.remove('border-interactive');
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

        this.#startFormElement.addEventListener('submit', (e) => {
            this.#startFormSubmit(e);
        });
        this.#endFormElement.addEventListener('submit', (e) => {
            this.#endFormSubmit(e);
        });
        this.#placeFormElement.addEventListener('submit', async(e) => {
            await this.#placesFormSubmit(e);
        });
    }

    #startFormSubmit(e) {
        e.preventDefault();

        sessionStorageList.setStartPlace();

        const modalType = 'start';
        modal.closeModal(modalType);
        modal.addButtonEvent(modalType);

        modal.changeStartDisplay(); // 表示を変える
    }

    #endFormSubmit(e) {
        e.preventDefault();

        sessionStorageList.setEndPlace();

        const modalType = 'end';
        modal.closeModal(modalType);
        modal.addButtonEvent(modalType);

        modal.changeEndDisplay(); // 表示を変える
    }

    async #placesFormSubmit(e) {
        e.preventDefault();

        const formId = e.target.id; // formのid取得
        const formNum = Number(formId.replace('placeForm', '')); // placeForm${num}の数字だけ取得

        sessionStorageList.setPlaces(formNum); // sessionにform内容を登録

        const modalType = 'places';
        modal.closeModal(modalType, formNum-1); // modalを閉じる
        modal.changePlaceDisplay(formNum); // placesの表示変更

        if(formNum !== placeNum.value()) return; // 目的地再設定のとき
        modal.addButtonEvent(modalType); // modalのイベント再アタッチ

        // 追加フラグメントの取得
        const newFragment = new Fragment();
        await newFragment.initialize();

        if (!newFragment.value()) return; // 取得できなかったとき

        newFragment.addFragment(); // フラグメントをHTML上に追加
        placeNum.increment();
        modal.addPlacesElement(); // 追加フラグメントにmodalイベントをアタッチ
        new ModalForm(); // modalFormイベントをアタッチ
    };
}

const placeNum = new PlaceNum();
const sessionStorageList = new SessionStorageList();
const modal = new ModalElement();

new ModalForm();
