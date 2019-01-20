import { element, by, ElementFinder } from 'protractor';

export class PoosheshComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('insutech-pooshesh div table .btn-danger'));
    title = element.all(by.css('insutech-pooshesh div h2#page-heading span')).first();

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

export class PoosheshUpdatePage {
    pageTitle = element(by.id('insutech-pooshesh-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    faalInput = element(by.id('field_faal'));
    aztarikhInput = element(by.id('field_aztarikh'));
    nerkhSelect = element(by.id('field_nerkh'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    getFaalInput() {
        return this.faalInput;
    }
    async setAztarikhInput(aztarikh) {
        await this.aztarikhInput.sendKeys(aztarikh);
    }

    async getAztarikhInput() {
        return this.aztarikhInput.getAttribute('value');
    }

    async nerkhSelectLastOption() {
        await this.nerkhSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async nerkhSelectOption(option) {
        await this.nerkhSelect.sendKeys(option);
    }

    getNerkhSelect(): ElementFinder {
        return this.nerkhSelect;
    }

    async getNerkhSelectedOption() {
        return this.nerkhSelect.element(by.css('option:checked')).getText();
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

export class PoosheshDeleteDialog {
    private dialogTitle = element(by.id('insutech-delete-pooshesh-heading'));
    private confirmButton = element(by.id('insutech-confirm-delete-pooshesh'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
