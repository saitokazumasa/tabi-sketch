class RecommendPlace {
    /**
     * おすすめ目的地submit時に発火
     */
    submitEvent(e) {
        e.preventDefault();
        // todo: sessionに値を追加する (modal.jsのSessionStorageListのような形で実装)

        // todo: toggleBtnの表示を変更する
        this.#changeDisplay();
    }

    /**
     * toggleBtnの表示を変更
     */
    #changeDisplay() {
        // 緑の枠線を消す
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
