import { element, by, ElementFinder } from 'protractor';

export class OnvanKhodroComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-onvan-khodro div table .btn-danger'));
    title = element.all(by.css('jhi-onvan-khodro div h2#page-heading span')).first();

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

export class OnvanKhodroUpdatePage {
    pageTitle = element(by.id('jhi-onvan-khodro-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    sharhInput = element(by.id('field_sharh'));
    azTarikhInput = element(by.id('field_azTarikh'));
    taTarikhInput = element(by.id('field_taTarikh'));
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

    async setAzTarikhInput(azTarikh) {
        await this.azTarikhInput.sendKeys(azTarikh);
    }

    async getAzTarikhInput() {
        return this.azTarikhInput.getAttribute('value');
    }

    async setTaTarikhInput(taTarikh) {
        await this.taTarikhInput.sendKeys(taTarikh);
    }

    async getTaTarikhInput() {
        return this.taTarikhInput.getAttribute('value');
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

export class OnvanKhodroDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-onvanKhodro-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-onvanKhodro'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
