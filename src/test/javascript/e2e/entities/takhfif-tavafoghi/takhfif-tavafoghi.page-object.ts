import { element, by, ElementFinder } from 'protractor';

export class TakhfifTavafoghiComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-takhfif-tavafoghi div table .btn-danger'));
    title = element.all(by.css('jhi-takhfif-tavafoghi div h2#page-heading span')).first();

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

export class TakhfifTavafoghiUpdatePage {
    pageTitle = element(by.id('jhi-takhfif-tavafoghi-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    darsadTakhfifInput = element(by.id('field_darsadTakhfif'));
    azTarikhInput = element(by.id('field_azTarikh'));
    faalInput = element(by.id('field_faal'));
    sherkatBimeSelect = element(by.id('field_sherkatBime'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameInput(name) {
        await this.nameInput.sendKeys(name);
    }

    async getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    async setDarsadTakhfifInput(darsadTakhfif) {
        await this.darsadTakhfifInput.sendKeys(darsadTakhfif);
    }

    async getDarsadTakhfifInput() {
        return this.darsadTakhfifInput.getAttribute('value');
    }

    async setAzTarikhInput(azTarikh) {
        await this.azTarikhInput.sendKeys(azTarikh);
    }

    async getAzTarikhInput() {
        return this.azTarikhInput.getAttribute('value');
    }

    getFaalInput() {
        return this.faalInput;
    }

    async sherkatBimeSelectLastOption() {
        await this.sherkatBimeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async sherkatBimeSelectOption(option) {
        await this.sherkatBimeSelect.sendKeys(option);
    }

    getSherkatBimeSelect(): ElementFinder {
        return this.sherkatBimeSelect;
    }

    async getSherkatBimeSelectedOption() {
        return this.sherkatBimeSelect.element(by.css('option:checked')).getText();
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

export class TakhfifTavafoghiDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-takhfifTavafoghi-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-takhfifTavafoghi'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
