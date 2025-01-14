class RecommendPlace {
    /**
     * おすすめ目的地submit時に発火
     */
    async submitEvent(e) {
        e.preventDefault();

        // submit送信したformを取得
        const formKey = e.target.id;
        const formNum = Number(formKey.replace('recommendForm', ''));

        // 「目的地を追加する」のform取得
        const modalForm = new ModalForm();
        const placeFormList = modalForm.placeFormElement; // modalFormクラスのformListを取得
        // 一番後ろのplaceFormの項番を取得
        const placeFormKey = placeFormList[placeFormList.length - 1].id;
        const placeFormNum = Number(placeFormKey.replace('placesSubmit', ''));

        // 今表示されている「目的地を追加する」のvalueを更新する
        this.#setPlaceFormValue(placeFormNum, e);

        // 今表示されている「目的地を追加する」の表示内容変更
        modal.changePlaceDisplay(placeFormNum);

        // 新規フラグメントの呼び出し
        await modalForm.newAddFragment();

        this.#hideModal(formNum); // 現在開かれてるmodalを閉じる
        this.#hideDisplay(formNum); // 追加したおすすめ目的地の表示を隠す
    }

    /**
     * modalの表示を隠す
     * @param formNum
     */
    #hideModal(formNum) {
        const modalElement = document.getElementById(`recommendModal${formNum}`);
        const modal = new Modal(modalElement);
        modal.hide(); // 表示されているmodalを閉じる
    }

    /**
     * toggleBtnの表示を隠す
     */
    #hideDisplay(formNum) {
        // modal表示切り替えのtoggleBtn取得
        const toggleBtn = document.getElementById(`recommendToggle${formNum}`);
        toggleBtn.style.display = 'none';
    }

    /**
     * sessionから値を取得してformのvalueに入れる
     * @param placeFormNum {number} 「目的地」formの項番
     * @param e {event}
     */
    #setPlaceFormValue(placeFormNum, e) {
        // FormDataを利用してフォームのデータを取得
        const formData = new FormData(e.target);
        // フォームデータをオブジェクトとして取得
        const formObject = Object.fromEntries(formData.entries());

        document.getElementById(`placeId${placeFormNum}`).value = formObject.googlePlaceId;
        document.getElementById(`placeLat${placeFormNum}`).value = formObject.latitude;
        document.getElementById(`placeLng${placeFormNum}`).value = formObject.longitude;
        document.getElementById(`place${placeFormNum}`).value = formObject.placeName;

        if (formObject.budget)
            document.getElementById(`budget${placeFormNum}`).value = formObject.budget;

        if (formObject.stayTime)
            document.getElementById(`stayTime${placeFormNum}`).value = formObject.stayTime;
        else
            document.getElementById(`stayTime${placeFormNum}`).value = 30;

        if (formObject.desiredStartTime)
            document.getElementById(`desiredStartTime${placeFormNum}`).value = formObject.desiredStartTime;

        if (formObject.desiredEndTime)
            document.getElementById(`desiredEndTime${placeFormNum}`).value = formObject.desiredEndTime;
    }
}

const recommendPlace = new RecommendPlace();

// th呼び出し後 submitイベントをアタッチ
document.addEventListener('DOMContentLoaded', function() {
    // 各form要素に recommend という独自クラスを付与し取得する
    document.querySelectorAll('.recommend').forEach(element => {
        element.addEventListener('submit', async function(e) {
            await recommendPlace.submitEvent(e);
        });
    });
});
