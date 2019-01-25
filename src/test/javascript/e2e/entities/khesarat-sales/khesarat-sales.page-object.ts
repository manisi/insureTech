import { element, by, ElementFinder } from 'protractor';

export class KhesaratSalesComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-khesarat-sales div table .btn-danger'));
    title = element.all(by.css('jhi-khesarat-sales div h2#page-heading span')).first();

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

export class KhesaratSalesUpdatePage {
    pageTitle = element(by.id('jhi-khesarat-sales-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    noeSelect = element(by.id('field_noe'));
    nerkhKhesaratInput = element(by.id('field_nerkhKhesarat'));
    faalInput = element(by.id('field_faal'));
    sabegheSelect = element(by.id('field_sabeghe'));
    noeSabegheSelect = element(by.id('field_noeSabeghe'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
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

    async setNerkhKhesaratInput(nerkhKhesarat) {
        await this.nerkhKhesaratInput.sendKeys(nerkhKhesarat);
    }

    async getNerkhKhesaratInput() {
        return this.nerkhKhesaratInput.getAttribute('value');
    }

    getFaalInput() {
        return this.faalInput;
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

export class KhesaratSalesDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-khesaratSales-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-khesaratSales'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
