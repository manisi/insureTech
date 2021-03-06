import { element, by, ElementFinder } from 'protractor';

export class SabegheKhesaratComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-sabeghe-khesarat div table .btn-danger'));
    title = element.all(by.css('jhi-sabeghe-khesarat div h2#page-heading span')).first();

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

export class SabegheKhesaratUpdatePage {
    pageTitle = element(by.id('jhi-sabeghe-khesarat-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    sharhInput = element(by.id('field_sharh'));
    faalInput = element(by.id('field_faal'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setSharhInput(sharh) {
        await this.sharhInput.sendKeys(sharh);
    }

    async getSharhInput() {
        return this.sharhInput.getAttribute('value');
    }

    getFaalInput() {
        return this.faalInput;
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

export class SabegheKhesaratDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-sabegheKhesarat-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-sabegheKhesarat'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
