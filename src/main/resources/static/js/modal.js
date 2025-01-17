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
        const timeSpan = document.getElementById('startTimeSpan'); // 時間spanタグ取得
        const placeSpan = document.getElementById('startPlaceSpan'); // 場所名spanタグ

        const startTime = document.getElementById('startTime');
        timeSpan.textContent = startTime.value; // 開始時間を入れる
        const startPlace = document.getElementById('startPlace');
        placeSpan.textContent = startPlace.value; // spanの文字を場所名に

        timeSpan.classList.remove('absolute');
    }

    /**
     * 終了地点の表示を変更
     */
    changeEndDisplay() {
        const placeSpan = document.getElementById('endPlaceSpan'); // spanタグ取得

        const endPlace = document.getElementById('endPlace');
        placeSpan.textContent = endPlace.value; // spanの文字を場所名に
    }

    /**
     * 目的地の表示を変更
     * @param formNum formの項番
     */
    changePlaceDisplay(formNum) {
        // buttonの子要素のspanタグ取得
        const timeSpan = document.getElementById(`placeTimeSpan${formNum}`);
        const placeSpan = document.getElementById(`placeNameSpan${formNum}`);
        const budgetSpan = document.getElementById(`budgetSpan${formNum}`);
        const stayTimeSpan = document.getElementById(`stayTimeSpan${formNum}`);

        // inputの要素取得
        const placeInput = document.getElementById(`place${formNum}`);
        const desiredStartTimeInput = document.getElementById(`desiredStartTime${formNum}`);
        const desiredEndTimeInput = document.getElementById(`desiredEndTime${formNum}`);
        const budgetInput = document.getElementById(`budget${formNum}`);
        const stayTimeInput = document.getElementById(`stayTime${formNum}`);

        /* ---- 表示を変更 ---- */
        placeInput.disabled = true; // 目的地部分をdisabledに
        placeInput.classList.add('bg-gray-100');

        // 場所名
        placeSpan.textContent = placeInput.value;

        // 希望時間
        if (!desiredStartTimeInput.value) timeSpan.textContent = '';
        else timeSpan.textContent = desiredStartTimeInput.value + '~' + desiredEndTimeInput.value;
        timeSpan.classList.remove('absolute');

        // 予算
        if (!budgetInput.value) budgetSpan.textContent = '予算：----' + '円';
        else budgetSpan.textContent = '予算：' + budgetInput.value + '円';

        // 滞在時間
        if (!stayTimeInput.value) stayTimeSpan.textContent = '滞在時間：30分';
        else stayTimeSpan.textContent = '滞在時間：' + stayTimeInput.value + '分';

        // 緑色の枠線をけす
        const toggleBtn = this.#getToggleBtn('places', formNum);
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
        document.getElementById('startError').textContent = '';

        const formData = new FormData(e.target);
        // api/create-planに送信
        this.postCreatePlaceAPI(formData, 'start');
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
        document.getElementById('endError').textContent = '';

        const formData = new FormData(e.target);

        // api/create-planに送信
        this.postCreatePlaceAPI(formData, 'end');
    }

    /**
     * 終了地点のrequiredチェック
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
        const formNum = Number(formId.replace('placeForm', '')); // placesSubmit{num}の数字だけ取得

        // 値の検証（nullがあるか）
        if (!this.#placeFormCheck(placeNum.value())) {
            document.getElementById(`placeError${placeNum.value()}`).textContent = '目的地を正しく入力してください。';
            return;
        }
        document.getElementById(`placeError${placeNum.value()}`).textContent = '';

        const formData = new FormData(e.target);
        this.setEndTime(formNum, formData);

        // Post処理 /api/create-places
        this.postCreatePlaceAPI(formData, 'places', formNum);

        if(formNum !== placeNum.value()) return; // 目的地再設定はreturn
        await this.newAddFragment();
    };

    /**
     * 目的地のrequiredチェック
     * @returns {boolean} すべて値が入ってたらtrue
     */
    #placeFormCheck(num) {
        const placeName = document.getElementById(`place${num}`).value;
        const placeId = document.getElementById(`placeId${num}`).value;
        const lat = document.getElementById(`placeLat${num}`).value;
        const lng = document.getElementById(`placeLng${num}`).value;

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

    /**
     *
     * @param formData 送信するformのデータ
     * @param modalType 送信するformのタイプ(start, end, places)
     * @param formNum 送信するformの項番 placeFormのみ
     */
    postCreatePlaceAPI(formData, modalType, formNum=null) {
        try {
            // 非同期でPOSTリクエストを送信
            fetch('/api/create-place', {
                method: 'POST',
                body: formData,
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`送信エラー: ${response.status}`);
                    }
                    return response.json(); // 必要に応じてレスポンスを処理
                })
                .then(data => {
                    if (data.status === 'Failed')
                        throw new Error('エラーが発生しました。');
                    // モーダル関連の動作
                    modal.closeModal(modalType, formNum);
                    if (modalType==='start') {
                        modal.changeStartDisplay();
                    } else if (modalType==='end') {
                        modal.changeEndDisplay();
                    } else {
                        modal.changePlaceDisplay(formNum);
                    }
                    modal.addButtonEvent(modalType, formNum); // 送信したmodalのイベント再アタッチ
                });
        } catch (error) {
            document.getElementById('Error').textContent = '送信中にエラーが発生しました。もう一度お試しください。';
        }
    }
}

const placeNum = new PlaceNum();
const modal = new ModalElement();

document.addEventListener('DOMContentLoaded', () => {
    new ModalForm();
});
