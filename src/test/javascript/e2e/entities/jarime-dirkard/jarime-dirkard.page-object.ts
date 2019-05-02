import { element, by, ElementFinder } from 'protractor';

export class JarimeDirkardComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-jarime-dirkard div table .btn-danger'));
    title = element.all(by.css('jhi-jarime-dirkard div h2#page-heading span')).first();

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

export class JarimeDirkardUpdatePage {
    pageTitle = element(by.id('jhi-jarime-dirkard-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    roozaneInput = element(by.id('field_roozane'));
    faalInput = element(by.id('field_faal'));
    grouhKhodroSelect = element(by.id('field_grouhKhodro'));
    sherkatBimeSelect = element(by.id('field_sherkatBime'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setRoozaneInput(roozane) {
        await this.roozaneInput.sendKeys(roozane);
    }

    async getRoozaneInput() {
        return this.roozaneInput.getAttribute('value');
    }

    getFaalInput() {
        return this.faalInput;
    }

    async grouhKhodroSelectLastOption() {
        await this.grouhKhodroSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async grouhKhodroSelectOption(option) {
        await this.grouhKhodroSelect.sendKeys(option);
    }

    getGrouhKhodroSelect(): ElementFinder {
        return this.grouhKhodroSelect;
    }

    async getGrouhKhodroSelectedOption() {
        return this.grouhKhodroSelect.element(by.css('option:checked')).getText();
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

export class JarimeDirkardDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-jarimeDirkard-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-jarimeDirkard'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
