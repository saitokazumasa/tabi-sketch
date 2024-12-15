class RecommendPlace {
    /**
     * おすすめ目的地submit時に発火
     */
    submitEvent(e) {
        e.preventDefault();

        // submit送信したformを取得
        const formKey = e.target.id;
        const formNum = Number(formKey.replace('recommendForm', ''));
        // todo: sessionに値を追加する (modal.jsのSessionStorageListのような形で実装)


        this.#hideDisplay(formNum);
    }

    /**
     * toggleBtnの表示を隠す
     */
    #hideDisplay(formNum) {
        // modal表示切り替えのtoggleBtn取得
        const toggleBtn = document.getElementById(`recommendToggle${formNum}`);
        toggleBtn.hidden = true;
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
