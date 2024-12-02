class RadioButtonManager {
    constructor(radioGroupName, svgClass) {
        this.radioButtons = document.querySelectorAll(`input[name="${radioGroupName}"]`);
        this.svgElements = document.querySelectorAll(`.${svgClass}`);
        if (this.radioButtons.length > 0 && this.svgElements.length > 0) {
            this.init();
        }
    }
    init() {
        this.radioButtons.forEach(radio => {
            radio.addEventListener('change', () => this.updateClasses());
        });
        this.updateClasses();
    }
    updateClasses() {
        this.radioButtons.forEach((radio, index) => {
            if (radio.checked) {
                this.svgElements[index].classList.remove('fill-label');
            } else {
                this.svgElements[index].classList.add('fill-label');
            }
        });
    }
}
const radioManager = new RadioButtonManager('fourTransportation', 'fill-label');
