import { element, by, ElementFinder } from 'protractor';

export class ModateBimenameComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-modate-bimename div table .btn-danger'));
    title = element.all(by.css('jhi-modate-bimename div h2#page-heading span')).first();

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

export class ModateBimenameUpdatePage {
    pageTitle = element(by.id('jhi-modate-bimename-heading'));
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

export class ModateBimenameDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-modateBimename-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-modateBimename'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
