import { element, by, ElementFinder } from 'protractor';

export class NerkhComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-nerkh div table .btn-danger'));
    title = element.all(by.css('jhi-nerkh div h2#page-heading span')).first();

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

export class NerkhUpdatePage {
    pageTitle = element(by.id('jhi-nerkh-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    faalInput = element(by.id('field_faal'));
    mablaghInput = element(by.id('field_mablagh'));
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

    getFaalInput() {
        return this.faalInput;
    }
    async setMablaghInput(mablagh) {
        await this.mablaghInput.sendKeys(mablagh);
    }

    async getMablaghInput() {
        return this.mablaghInput.getAttribute('value');
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

export class NerkhDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-nerkh-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-nerkh'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
