class RecommendPlace {
    /**
     * おすすめ目的地submit時に発火
     */
    submitEvent(e) {
        e.preventDefault();

        // submit送信したformを取得
        const formKey = e.target.id;
        const formNum = Number(formKey.replace('recommendForm', ''));

        // todo: sessionに値を追加 (modal.jsのSessionStorageListのplaceに入れる)
        // todo: 今表示されている「目的地を追加する」のvalueをsessionから入れる（PR#194 modal.js setPlaceFormValue のような形）
        // todo: 今表示されている「目的地を追加する」の表示内容変更（PR#194 modal.js changePlaceDisplay のような形）
        // todo: 新規フラグメントの呼び出し（PR#194 modal.js placesFormSubmit のような形）

        this.#hideModal(formNum);
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
}

const recommendPlace = new RecommendPlace();

// th呼び出し後 submitイベントをアタッチ
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.recommend').forEach(element => {
        element.addEventListener('submit', function(e) {
            recommendPlace.submitEvent(e);
        });
    });
});
