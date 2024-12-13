class CopyLinkButton {
    constructor(copyLinkButton, copyResultElement) {
        this.copyButton = document.getElementById(copyLinkButton);
        this.resultElement = document.getElementById(copyResultElement);
        this.toggleButton = document.getElementById('toggleButton');

        // TODO:DBのリンク共有可能かどうかの状態を取得する。
        this.toggleButton.addEventListener('change', () => this.checkToggleButton());

        if(!this.copyButton) return;
        this.copyButton.addEventListener('click', () => this.copyClipboard());
    }

    checkToggleButton(){
        if(this.toggleButton.checked) {
            this.copyButton.classList.remove('hidden');
            return;
        }
        this.copyButton.classList.add('hidden');
    }

    copyClipboard() {
        const currentUrl = window.location.href;

        navigator.clipboard.writeText(currentUrl)
            .then(() => {
                this.showResultMessage();
            })
            .catch(err => {
                console.error('リンクのコピーに失敗しました:', err);
            });
    }

    showResultMessage(){
        if (!this.resultElement) return;
        this.resultElement.classList.remove('hidden');
        setTimeout(() => {
            this.resultElement.classList.add('hidden');
        }, 2000);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new CopyLinkButton('copyLinkButton', 'copyResultElement');
});
