import { element, by, ElementFinder } from 'protractor';

export class SherkatBimeComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('insutech-sherkat-bime-bimisho div table .btn-danger'));
    title = element.all(by.css('insutech-sherkat-bime-bimisho div h2#page-heading span')).first();

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

export class SherkatBimeUpdatePage {
    pageTitle = element(by.id('insutech-sherkat-bime-bimisho-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
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

export class SherkatBimeDeleteDialog {
    private dialogTitle = element(by.id('insutech-delete-sherkatBime-heading'));
    private confirmButton = element(by.id('insutech-confirm-delete-sherkatBime'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
