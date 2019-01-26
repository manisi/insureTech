import { element, by, ElementFinder } from 'protractor';

export class KhesaratSrneshinComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-khesarat-srneshin div table .btn-danger'));
    title = element.all(by.css('jhi-khesarat-srneshin div h2#page-heading span')).first();

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

export class KhesaratSrneshinUpdatePage {
    pageTitle = element(by.id('jhi-khesarat-srneshin-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nerkhKhesaratInput = element(by.id('field_nerkhKhesarat'));
    faalInput = element(by.id('field_faal'));
    noeSabegheSelect = element(by.id('field_noeSabeghe'));
    sabegheSelect = element(by.id('field_sabeghe'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNerkhKhesaratInput(nerkhKhesarat) {
        await this.nerkhKhesaratInput.sendKeys(nerkhKhesarat);
    }

    async getNerkhKhesaratInput() {
        return this.nerkhKhesaratInput.getAttribute('value');
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

export class KhesaratSrneshinDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-khesaratSrneshin-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-khesaratSrneshin'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
