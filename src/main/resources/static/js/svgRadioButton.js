class RadioButtonList {
    constructor(radioSelector, svgSelector) {
        this.radios = document.querySelectorAll(radioSelector);
        this.svgs = document.querySelectorAll(svgSelector);
        this.init();
    }

    init() {
        this.radios.forEach((radio) => {
            radio.addEventListener('change', () => this.updateSVGClasses());
        });

        this.updateSVGClasses();
    }

    updateSVGClasses() {
        this.svgs.forEach((svg) => svg.classList.add('fill-label'));

        this.radios.forEach((radio) => {
            if (!radio.checked) return;
            const label = document.querySelector(`label[for="${radio.id}"]`);
            const svg = label.querySelector('svg');
            if (!svg) return ;
            svg.classList.remove('fill-label');
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new RadioButtonList('input[type="radio"][name="fourTransportation"]', 'label > svg');
});
