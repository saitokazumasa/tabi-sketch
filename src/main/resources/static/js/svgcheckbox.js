
class ToggleCheckbox {
    constructor(checkboxId, targetElementId) {
        this.checkbox = document.getElementById(checkboxId);
        this.targetElement = document.getElementById(targetElementId);

        if (this.checkbox && this.targetElement) {
            this.init();
        }
    }
    init() {
        this.checkbox.addEventListener('change', () => this.toggle());
    }
   toggle() {
        if (this.checkbox.checked) {
            this.targetElement.classList.remove('fill-label');
        } else {
            this.targetElement.classList.add('fill-label');
        }
    }
}

const managers = [
    new ToggleCheckbox('checkbox1', 'targetElement1'),
    new ToggleCheckbox('checkbox2', 'targetElement2'),
    new ToggleCheckbox('checkbox3', 'targetElement3')
];
