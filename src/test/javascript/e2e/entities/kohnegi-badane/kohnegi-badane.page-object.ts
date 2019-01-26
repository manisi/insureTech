import { element, by, ElementFinder } from 'protractor';

export class KohnegiBadaneComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-kohnegi-badane div table .btn-danger'));
    title = element.all(by.css('jhi-kohnegi-badane div h2#page-heading span')).first();

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

export class KohnegiBadaneUpdatePage {
    pageTitle = element(by.id('jhi-kohnegi-badane-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    darsadHarSalInput = element(by.id('field_darsadHarSal'));
    maxDarsadInput = element(by.id('field_maxDarsad'));
    sharhInput = element(by.id('field_sharh'));
    faalInput = element(by.id('field_faal'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDarsadHarSalInput(darsadHarSal) {
        await this.darsadHarSalInput.sendKeys(darsadHarSal);
    }

    async getDarsadHarSalInput() {
        return this.darsadHarSalInput.getAttribute('value');
    }

    async setMaxDarsadInput(maxDarsad) {
        await this.maxDarsadInput.sendKeys(maxDarsad);
    }

    async getMaxDarsadInput() {
        return this.maxDarsadInput.getAttribute('value');
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

export class KohnegiBadaneDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-kohnegiBadane-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-kohnegiBadane'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
