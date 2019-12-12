import { element, by, ElementFinder } from 'protractor';

export class SaalSakhtComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-saal-sakht div table .btn-danger'));
    title = element.all(by.css('jhi-saal-sakht div h2#page-heading span')).first();

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

export class SaalSakhtUpdatePage {
    pageTitle = element(by.id('jhi-saal-sakht-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    saalShamsiInput = element(by.id('field_saalShamsi'));
    saalMiladiInput = element(by.id('field_saalMiladi'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setSaalShamsiInput(saalShamsi) {
        await this.saalShamsiInput.sendKeys(saalShamsi);
    }

    async getSaalShamsiInput() {
        return this.saalShamsiInput.getAttribute('value');
    }

    async setSaalMiladiInput(saalMiladi) {
        await this.saalMiladiInput.sendKeys(saalMiladi);
    }

    async getSaalMiladiInput() {
        return this.saalMiladiInput.getAttribute('value');
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

export class SaalSakhtDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-saalSakht-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-saalSakht'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
