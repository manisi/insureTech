import { element, by, ElementFinder } from 'protractor';

export class KhodroComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-khodro div table .btn-danger'));
    title = element.all(by.css('jhi-khodro div h2#page-heading span')).first();

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

export class KhodroUpdatePage {
    pageTitle = element(by.id('jhi-khodro-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    modelInput = element(by.id('field_model'));
    faalInput = element(by.id('field_faal'));
    noeSelect = element(by.id('field_noe'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setModelInput(model) {
        await this.modelInput.sendKeys(model);
    }

    async getModelInput() {
        return this.modelInput.getAttribute('value');
    }

    getFaalInput() {
        return this.faalInput;
    }
    async setNoeSelect(noe) {
        await this.noeSelect.sendKeys(noe);
    }

    async getNoeSelect() {
        return this.noeSelect.element(by.css('option:checked')).getText();
    }

    async noeSelectLastOption() {
        await this.noeSelect
            .all(by.tagName('option'))
            .last()
            .click();
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

export class KhodroDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-khodro-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-khodro'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
