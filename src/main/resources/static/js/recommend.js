class RecommendPlace {
    /**
     * おすすめ目的地submit時に発火
     */
    async submitEvent(e) {
        e.preventDefault();

        // submit送信したformを取得
        const formKey = e.target.id;
        const formNum = Number(formKey.replace('recommendSubmit', ''));

        // 「目的地を追加する」のform取得
        const modalSubmitButton = new ModalSubmitButton();
        const placeBtnList = modalSubmitButton.placeBtnElement;
        const placeBtnKey = placeBtnList[placeBtnList.length - 1].id;
        const placeBtnNum = Number(placeBtnKey.replace('placesSubmit', ''));

        // sessionに値を追加 (modal.jsのSessionStorageListのplaceに入れる)
        sessionStorageList.setRecommendPlace(placeBtnNum, formNum);

        // 今表示されている「目的地を追加する」のvalueをsessionから入れる
        this.#setPlaceFormValue(placeBtnNum);

        // 今表示されている「目的地を追加する」の表示内容変更
        modal.changePlaceDisplay(placeBtnNum);

        // 新規フラグメントの呼び出し
        await modalSubmitButton.newAddFragment();

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
     * @param placeBtnNum 「目的地」formの項番
     */
    #setPlaceFormValue(placeBtnNum) {
        const data = sessionStorageList.getPlacesData(placeBtnNum-1);

        document.getElementById(`placeId${placeBtnNum}`).value = data.placeId;
        document.getElementById(`placeLat${placeBtnNum}`).value = data.lat;
        document.getElementById(`placeLng${placeBtnNum}`).value = data.lng;
        document.getElementById(`place${placeBtnNum}`).value = data.name;

        if (data.budget)
            document.getElementById(`budget${placeBtnNum}`).value = data.budget;

        if (data.stayTime)
            document.getElementById(`stayTime${placeBtnNum}`).value = data.stayTime;
        else
            document.getElementById(`stayTime${placeBtnNum}`).value = 30;

        if (data.desiredStartTime)
            document.getElementById(`desiredStartTime${placeBtnNum}`).value = data.desiredStartTime;

        if (data.desiredEndTime)
            document.getElementById(`desiredEndTime${placeBtnNum}`).value = data.desiredEndTime;
    }
}

const recommendPlace = new RecommendPlace();

// th呼び出し後 submitイベントをアタッチ
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.recommend').forEach(element => {
        element.addEventListener('click', async function(e) {
            await recommendPlace.submitEvent(e);
        });
    });
});
