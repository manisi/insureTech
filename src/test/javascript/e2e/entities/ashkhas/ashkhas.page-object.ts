import { element, by, ElementFinder } from 'protractor';

export class AshkhasComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('insutech-ashkhas div table .btn-danger'));
    title = element.all(by.css('insutech-ashkhas div h2#page-heading span')).first();

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

export class AshkhasUpdatePage {
    pageTitle = element(by.id('insutech-ashkhas-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    firstNameInput = element(by.id('field_firstName'));
    lastNameInput = element(by.id('field_lastName'));
    emailInput = element(by.id('field_email'));
    phoneNumberInput = element(by.id('field_phoneNumber'));
    hireDateInput = element(by.id('field_hireDate'));
    noeShakhsSelect = element(by.id('field_noeShakhs'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setFirstNameInput(firstName) {
        await this.firstNameInput.sendKeys(firstName);
    }

    async getFirstNameInput() {
        return this.firstNameInput.getAttribute('value');
    }

    async setLastNameInput(lastName) {
        await this.lastNameInput.sendKeys(lastName);
    }

    async getLastNameInput() {
        return this.lastNameInput.getAttribute('value');
    }

    async setEmailInput(email) {
        await this.emailInput.sendKeys(email);
    }

    async getEmailInput() {
        return this.emailInput.getAttribute('value');
    }

    async setPhoneNumberInput(phoneNumber) {
        await this.phoneNumberInput.sendKeys(phoneNumber);
    }

    async getPhoneNumberInput() {
        return this.phoneNumberInput.getAttribute('value');
    }

    async setHireDateInput(hireDate) {
        await this.hireDateInput.sendKeys(hireDate);
    }

    async getHireDateInput() {
        return this.hireDateInput.getAttribute('value');
    }

    async setNoeShakhsSelect(noeShakhs) {
        await this.noeShakhsSelect.sendKeys(noeShakhs);
    }

    async getNoeShakhsSelect() {
        return this.noeShakhsSelect.element(by.css('option:checked')).getText();
    }

    async noeShakhsSelectLastOption() {
        await this.noeShakhsSelect
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

export class AshkhasDeleteDialog {
    private dialogTitle = element(by.id('insutech-delete-ashkhas-heading'));
    private confirmButton = element(by.id('insutech-confirm-delete-ashkhas'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
