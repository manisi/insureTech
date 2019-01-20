import { element, by, ElementFinder } from 'protractor';

export class CityComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('insutech-city div table .btn-danger'));
    title = element.all(by.css('insutech-city div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CityUpdatePage {
    pageTitle = element(by.id('insutech-city-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    tipsSelect = element(by.id('field_tips'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async tipsSelectLastOption() {
        await this.tipsSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async tipsSelectOption(option) {
        await this.tipsSelect.sendKeys(option);
    }

    getTipsSelect(): ElementFinder {
        return this.tipsSelect;
    }

    async getTipsSelectedOption() {
        return this.tipsSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class CityDeleteDialog {
    private dialogTitle = element(by.id('insutech-delete-city-heading'));
    private confirmButton = element(by.id('insutech-confirm-delete-city'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
