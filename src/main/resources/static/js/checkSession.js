class session {
    constructor() {
        this.session = {
            startPlace: [],
            place: [],
            endPlace: []
        };
        this.messageElement = document.querySelector('.text-danger.my-4');
        this.walkFlagCheckbox = document.getElementById('walkFlag');
        this.walkingTimeInput = document.getElementById('walkingTime');

        this.initEventListeners();
    }
    initEventListeners() {
        if (this.walkFlagCheckbox && this.walkingTimeInput) {
            this.walkFlagCheckbox.addEventListener('change', () => {
                this.walkingTimeInput.disabled = !this.walkFlagCheckbox.checked;
            });
        }
        const textInputs = document.querySelectorAll('input[type="text"]');
        textInputs.forEach(input => {
            input.addEventListener('input', () => this.handleInputChange(input));
        });

        this.checkInputs();
    }

    handleInputChange(input) {
        const id = input.getAttribute('id');

        switch (id) {
        case 'startPlace':
            this.session.startPlace = this.updateSessionArray(input.value);
            break;
        case 'place':
            this.session.place = this.updateSessionArray(input.value);
            break;
        case 'endPlace':
            this.session.endPlace = this.updateSessionArray(input.value);
            break;
        }

        this.checkInputs();
    }

    updateSessionArray(value) {
        return value.split(/[\s\n]+/).filter(item => item.trim() !== '');
    }

    // どれか一つでも入力されているかチェックし、入力がない場合はメッセージを表示する
    checkInputs() {
        const isAnyInputEntered = [
            this.session.startPlace,
            this.session.place,
            this.session.endPlace
        ].some(array => array.length > 0);

        if (this.messageElement) {
            this.messageElement.style.display = isAnyInputEntered ? 'none' : 'block';
        }
    }
    getSession() {
        return { ...this.session };
    }
}

document.addEventListener('DOMContentLoaded', () => {
    window.session = new session();
});
