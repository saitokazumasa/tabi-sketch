class CopyLinkButton {
    constructor(copyLinkButton, copyResultElement) {
        this.button = document.getElementById(copyLinkButton);
        this.resultElement = document.getElementById(copyResultElement);

        if(!this.button) return;
        this.button.addEventListener('click', () => this.copyClipboard());
    }
    copyClipboard() {
        const currentUrl = window.location.href;

        navigator.clipboard.writeText(currentUrl)
            .then(() => {
                this.showResultMessage();
            })
            .catch(err => {
                console.error('リンクのコピーに失敗しました:', err);
                alert('リンクをコピーできませんでした。');
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
