class PlaceNum {
    #value = 0;

    value() {
        return this.#value;
    }

    increment() {
        this.#value ++;
    }
}

class Fragment {
    #toggle;
    #form;

    constructor() {
        this.#toggle = null;
        this.#form = null;
    }

    /**
     * 追加fragmentの初期化
     * @returns {Promise<void>}
     */
    async initialize() {
        try {
            const response = await fetch(`/fragment/modal/placesToggle?num=${(placeNum.value()+1)}`);
            if (!response.ok) { throw new Error('フラグメントの取得に失敗しました'); }
            this.#toggle = await response.text();
        } catch (error) {
            throw new Error('initialize Error : ' + error);
        }
        try {
            const response = await fetch(`fragment/modal/placesForm?num=${(placeNum.value()+1)}`);
            if (!response.ok) { throw new Error('フラグメントの取得に失敗しました'); }
            this.#form = await response.text();
        } catch (error) {
            throw new Error('initialize Error : ' + error);
        }
    }

    /**
     * HTMLにfragment追加
     */
    addFragment() {
        if (this.#toggle === null) throw new Error('このインスタンスは初期化されていません。initialize()を実行してください。');
        // id=destination の子要素にToggleを追加
        const container = document.getElementById('destination');
        const newToggle = document.createElement('div');
        newToggle.innerHTML = this.#toggle;
        container.appendChild(newToggle);
        // mainタグの子要素に Form を追加
        const main = document.querySelector('main');
        const newForm = document.createElement('div');
        newForm.innerHTML = this.#form;
        main.appendChild(newForm);

    }

    /**
     * 追加する Toggle fragment取得
     * @returns {*}
     */
    toggle() {
        if (this.#toggle === null) throw new Error('このインスタンスは初期化されていません。initialize()を実行してください。');
        return this.#toggle;
    }

    /**
     * 追加する Form fragment取得
     * @returns {*}
     */
    form() {
        if (this.#form === null) throw new Error('このインスタンスは初期化されていません。initialize()を実行してください。');
        return this.#form;
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

    /**
     * 目的地のelementを配列に追加・autocomplete適用
     */
    addPlacesElement() {
        const modal = document.getElementById(`placeModal${placeNum.value()}`);
        const toggleButton = document.getElementById(`placeToggleBtn${placeNum.value()}`);
        const closeButton = document.getElementById(`placeCloseBtn${placeNum.value()}`);

        this.#modals.places.push(modal);
        this.#toggleButtons.places.push(toggleButton);
        this.#closeButtons.places.push(closeButton);

        const inputElement = document.getElementById(`place${placeNum.value()}`);
        const autoComplete = new AutoComplete(inputElement);
        autoCompleteList.add(autoComplete);
    }

    /**
     * ModalButtonイベント アタッチ
     * @param modalType (start,end,places)が入る
     * @Param num formId-1
     */
    addButtonEvent(modalType, num) {
        const modal = this.#getModal(modalType, num);
        const toggleBtn = this.#getToggleBtn(modalType, num);
        const closeBtn = this.#getCloseBtn(modalType);

        // イベントのアタッチ
        toggleBtn.addEventListener('click', () => modal.toggle() );
        closeBtn.addEventListener('click', () => {
            modal.hide();
            document.activeElement.blur(); // フォーカスを外す
        });
    }

    /**
     * modal取得
     * @param modalType {'places','start','end'} モーダルの種別
     * @param num formNum-1
     * @returns {Modal}
     */
    #getModal(modalType, num) {
        if (modalType === 'places') {
            return new Modal(this.#modals[modalType][num]);
        }
        return new Modal(this.#modals[modalType]);
    }

    /**
     * toggle取得
     * @param modalType {'places','start','end'} モーダルの種別
     * @param num formNum-1
     * @returns {*}
     */
    #getToggleBtn(modalType, num) {
        if (modalType === 'places') {
            return this.#toggleButtons.places[num];
        }
        return this.#toggleButtons[modalType];
    }

    /**
     * close取得
     * @param modalType {'places','start','end'} モーダルの種別
     * @returns {*}
     */
    #getCloseBtn(modalType) {
        if (modalType === 'places') {
            return this.#closeButtons.places[placeNum.value()-1];
        }
        return this.#closeButtons[modalType];
    }

    /**
     * modalを閉じる
     * @param modalType {'places','start','end'} モーダルの種別
     * @param num modalListのnumber(form項番-1)
     */
    closeModal(modalType, num) {
        const modal = this.#getModal(modalType, num);
        modal.hide();
    }

    /**
     * 出発地点の表示を変更
     */
    changeStartDisplay() {
        const spans = document.querySelectorAll('#startToggle span'); // spanタグ取得

        // todo: 表示変更する値を取得 data
        // spans[0].textContent = data.startTime; // 開始時間を入れる
        // spans[1].textContent = data.name; // spanの文字を場所名に
        spans[0].classList.remove('absolute');
    }

    /**
     * 終了地点の表示を変更
     */
    changeEndDisplay() {
        const span = document.querySelector('#endToggle span'); // spanタグ取得

        // todo: 表示変更するspanの文字を変えて
        // span.textContent = sessionStorageList.getEndData().name; // spanの文字を場所名に
    }

    /**
     * 目的地の表示を変更
     * @param formNum formの項番
     */
    changePlaceDisplay(formNum) {
        // buttonの子要素のspanタグ取得
        const spans = document.querySelectorAll(`#placeToggleBtn${formNum} > span`);
        const placeInput = document.getElementById(`place${formNum}`);

        placeInput.disabled = true; // 目的地部分をdisabledに
        placeInput.classList.add('bg-gray-100');

        // todo: 表示変更する値を取得 data

        // const data = sessionStorageList.getPlacesData(formNum-1);

        // // 場所名
        // spans[1].textContent = data.name;
        //
        // // 希望時間
        // if (!data.desiredStartTime) spans[0].textContent = '';
        // else spans[0].textContent = data.desiredStartTime + '~' + data.desiredEndTime;
        // spans[0].classList.remove('absolute');
        //
        // // 予算
        // const budget = document.getElementById(`budgetDisplay${formNum}`);
        // if (!data.budget) budget.textContent = '予算：----' + '円';
        // else budget.textContent = '予算：' + data.budget + '円';
        //
        // // 滞在時間
        // const stayTime = document.getElementById(`stayTimeDisplay${formNum}`);
        // if (!data.stayTime) stayTime.textContent = '滞在時間：30分';
        // else stayTime.textContent = '滞在時間：' + data.stayTime + '分';

        // 緑色の枠線をけす
        const toggleBtn = this.#getToggleBtn('places', formNum-1);
        toggleBtn.classList.remove('border-interactive');

        this.#displayDeleteButton(formNum);
    }

    /**
     * ✕ボタンの表示
     * @param formNum formの項番
     */
    #displayDeleteButton(formNum) {
        const deleteBtn = document.getElementById(`modalDeleteBtn${formNum}`);
        deleteBtn.classList.remove('hidden');

        deleteBtn.addEventListener('click', () => {
            const toggleBtn = this.#getToggleBtn('places', formNum-1);
            toggleBtn.classList.add('hidden'); // Modal表示のボタンを隠す
            deleteBtn.classList.add('hidden'); // ✕ボタンを隠す
        });
    }
}

class ModalForm {
    #startFormElement;
    placeFormElement = [];
    #endFormElement;

    constructor() {
        this.#startFormElement = document.getElementById('startPlaceForm');
        for (let i = 0; i <= placeNum.value(); i++) {
            this.placeFormElement.push(document.getElementById(`placeForm${i}`));
        }
        this.#endFormElement = document.getElementById('endPlaceForm');
        this.initFormEvent();
    }

    initFormEvent() {
        if (!this.#startFormElement || !this.placeFormElement || !this.#endFormElement) return;
        this.#startFormElement.addEventListener('submit', (e) => this.#startFormSubmit(e) );
        this.#endFormElement.addEventListener('submit', (e) => this.#endFormSubmit(e) );
        this.placeFormElement.forEach((element) => element.addEventListener('submit', async(e) => await this.#placesFormSubmit(e)));
    }

    /**
     * 出発地点のsubmitイベント
     * @param e イベント
     */
    #startFormSubmit(e) {
        e.preventDefault();

        // 値の検証（nullがあるか）
        if (!this.#startFormCheck()) {
            // エラーメッセージ表示
            document.getElementById('startError').textContent = '出発地点・予定時間を正しく入力してください。';
            return;
        }

        sessionStorageList.setStartPlace();

        const modalType = 'start';
        modal.closeModal(modalType, 0);
        modal.addButtonEvent(modalType, 0);

        modal.changeStartDisplay(); // 表示を変える
    }

    /**
     * 出発地点のrequiredチェック
     * @returns {boolean} すべて値が入ってたらtrue
     */
    #startFormCheck() {
        const placeName = document.getElementById('startPlace').value;
        const placeId = document.getElementById('startPlaceId').value;
        const lat = document.getElementById('startLat').value;
        const lng = document.getElementById('startLng').value;
        const time = document.getElementById('startTime').value;

        return !!(placeName && placeId && lat && lng && time);
    }

    /**
     * 終了地点のsubmitイベント
     * @param e イベント
     */
    #endFormSubmit(e) {
        e.preventDefault();

        // 値の検証（nullがあるか）
        if (!this.#endFormCheck()) {
            document.getElementById('endError').textContent = '終了地点を正しく入力してください。';
            return;
        }

        const modalType = 'end';
        modal.closeModal(modalType, 0);
        modal.addButtonEvent(modalType, 0);

        modal.changeEndDisplay(); // 表示を変える
    }

    /**
     * 出発地点のrequiredチェック
     * @returns {boolean} すべて値が入ってたらtrue
     */
    #endFormCheck() {
        const placeName = document.getElementById('endPlace').value;
        const placeId = document.getElementById('endPlaceId').value;
        const lat = document.getElementById('endLat').value;
        const lng = document.getElementById('endLng').value;

        return !!(placeName && placeId && lat && lng);
    }

    /**
     * 目的地のsubmitイベント
     * @param e イベント
     * @returns {Promise<void>}
     */
    async #placesFormSubmit(e) {
        e.preventDefault();

        const formId = e.target.id; // formのid取得
        const formNum = Number(formId.replace('placesSubmit', '')); // placesSubmit{num}の数字だけ取得

        // 値の検証（nullがあるか）
        if (!this.#placeFormCheck(placeNum)) {
            document.getElementById(`placeError${placeNum}`).textContent = '目的地を正しく入力してください。';
            return;
        }

        // modal設定
        const modalType = 'places';
        modal.closeModal(modalType, placeNum);
        modal.changePlaceDisplay(placeNum);
        modal.addButtonEvent(modalType, placeNum); // 送信したmodalのイベント再アタッチ

        if(placeNum !== placeNum.value()) return; // 目的地再設定はreturn

        await this.newAddFragment();
    };

    /**
     * 出発地点のrequiredチェック
     * @returns {boolean} すべて値が入ってたらtrue
     */
    #placeFormCheck(placeNum) {
        const placeName = document.getElementById(`place${placeNum}`).value;
        const placeId = document.getElementById(`placeId${placeNum}`).value;
        const lat = document.getElementById(`placeLat${placeNum}`).value;
        const lng = document.getElementById(`placeLng${placeNum}`).value;

        return !!(placeName && placeId && lat && lng);
    }

    /**
     * 追加フラグメントを挿入
     * @returns {Promise<void>}
     */
    async newAddFragment() {
        const newFragment = new Fragment();
        await newFragment.initialize();

        if (!newFragment.toggle() && !newFragment.form()) return; // 取得できなかったとき

        newFragment.addFragment();
        placeNum.increment();
        modal.addPlacesElement();
        modal.addButtonEvent('places', placeNum.value()); // 新しいModalにイベント追加
        new ModalForm(); // modalFormイベントをアタッチ
    }
}

const placeNum = new PlaceNum();
const modal = new ModalElement();

document.addEventListener('DOMContentLoaded', () => {
    new ModalForm();
});
