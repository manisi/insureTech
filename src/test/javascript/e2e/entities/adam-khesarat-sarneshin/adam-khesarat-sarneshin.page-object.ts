import { element, by, ElementFinder } from 'protractor';

export class AdamKhesaratSarneshinComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-adam-khesarat-sarneshin div table .btn-danger'));
    title = element.all(by.css('jhi-adam-khesarat-sarneshin div h2#page-heading span')).first();

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

export class AdamKhesaratSarneshinUpdatePage {
    pageTitle = element(by.id('jhi-adam-khesarat-sarneshin-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    sarneshinInput = element(by.id('field_sarneshin'));
    faalInput = element(by.id('field_faal'));
    noeSabegheSelect = element(by.id('field_noeSabeghe'));
    sabegheSelect = element(by.id('field_sabeghe'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setSarneshinInput(sarneshin) {
        await this.sarneshinInput.sendKeys(sarneshin);
    }

    async getSarneshinInput() {
        return this.sarneshinInput.getAttribute('value');
    }

    getFaalInput() {
        return this.faalInput;
    }

    async noeSabegheSelectLastOption() {
        await this.noeSabegheSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async noeSabegheSelectOption(option) {
        await this.noeSabegheSelect.sendKeys(option);
    }

    getNoeSabegheSelect(): ElementFinder {
        return this.noeSabegheSelect;
    }

    async getNoeSabegheSelectedOption() {
        return this.noeSabegheSelect.element(by.css('option:checked')).getText();
    }

    async sabegheSelectLastOption() {
        await this.sabegheSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async sabegheSelectOption(option) {
        await this.sabegheSelect.sendKeys(option);
    }

    getSabegheSelect(): ElementFinder {
        return this.sabegheSelect;
    }

    async getSabegheSelectedOption() {
        return this.sabegheSelect.element(by.css('option:checked')).getText();
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

export class AdamKhesaratSarneshinDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-adamKhesaratSarneshin-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-adamKhesaratSarneshin'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
