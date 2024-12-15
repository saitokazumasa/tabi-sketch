class SortableManager {
    constructor(listId) {
        this.listElement = document.getElementById(listId);
        if (!this.listElement) return;

        this.sortable = new Sortable(this.listElement, {
            animation: 150,
            onEnd: () => this.updateIds(),
        });
    }

    updateIds() {
        const listItems = this.listElement.querySelectorAll('li');
        listItems.forEach((item, index) => {
            item.id = `item-${index + 1}`;
            console.log(item.id);
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new SortableManager('destination');
});
