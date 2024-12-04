class RadioButton {
    #element;
    #svgElement;
    #parentList;

    constructor(element, svgElement,parentList) {
        this.#element = element;
        this.#svgElement = svgElement;
        this.#parentList = parentList;
        this.#element.addEventListener('change', () => this.#parentList.onChecked());
        this.onChanged();
    }

    onChanged() {
        if (this.#element.checked) {
            this.#svgElement.classList.remove('fill-label');
            return;
        }
        this.#svgElement.classList.add('fill-label');
    }
}

class RadioButtonList {
    #values = [];

    constructor(radioGroupName, svgClass) {
        const radioButtonElements = document.querySelectorAll(`input[name="${radioGroupName}"]`);
        const svgElements = document.querySelectorAll(`.${svgClass}`);

        if (radioButtonElements.length <= 0 || svgElements.length <= 0) return;

        radioButtonElements.forEach((radio, index) => {
            const radioButton = new RadioButton(radio, svgElements[index],this);
            this.#values.push(radioButton);
        });
    }

    onChecked() {
        this.#values.forEach((radioButton) => {
            radioButton.onChanged();
        });
    }
}
const radioButtonList = new RadioButtonList('fourTransportation', 'fill-label');
