class ToggleButton {
    constructor(toggleId, sendUrl) {
        this.toggleButton = document.getElementById(toggleId);
        this.sendUrl = sendUrl;

        if (!this.toggleButton) return;

        this.initialize();
    }

    initialize() {
        console.log(this.sendUrl);
        this.toggleButton.addEventListener('change', () => this.toggleChange());
    }

    async toggleChange() {
        try{
            const isPublic = this.toggleButton.checked;
            const response = await fetch(`${this.sendUrl}?is_public=${isPublic}`);
            if(response) return;
            throw new Error('取得に失敗しました。');
        }catch (error){
            console.log(error);
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    //TODO:sendUrlをcontroller側のis_publicを変更する時のUrlに変える
    new ToggleButton('toggleButton', '/onodera/public');
});
