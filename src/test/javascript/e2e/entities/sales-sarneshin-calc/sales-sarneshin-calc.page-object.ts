import { element, by, ElementFinder } from 'protractor';

export class SalesSarneshinCalcComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-sales-sarneshin-calc div table .btn-danger'));
    title = element.all(by.css('jhi-sales-sarneshin-calc div h2#page-heading span')).first();

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

export class SalesSarneshinCalcUpdatePage {
    pageTitle = element(by.id('jhi-sales-sarneshin-calc-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    mablaghSalesMazadInput = element(by.id('field_mablaghSalesMazad'));
    tedadRoozInput = element(by.id('field_tedadRooz'));
    mablaghHaghBimeInput = element(by.id('field_mablaghHaghBime'));
    tarikhShorooInput = element(by.id('field_tarikhShoroo'));
    tarikhPayanInput = element(by.id('field_tarikhPayan'));
    namesherkatSelect = element(by.id('field_namesherkat'));
    grouhKhodroSelect = element(by.id('field_grouhKhodro'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setMablaghSalesMazadInput(mablaghSalesMazad) {
        await this.mablaghSalesMazadInput.sendKeys(mablaghSalesMazad);
    }

    async getMablaghSalesMazadInput() {
        return this.mablaghSalesMazadInput.getAttribute('value');
    }

    async setTedadRoozInput(tedadRooz) {
        await this.tedadRoozInput.sendKeys(tedadRooz);
    }

    async getTedadRoozInput() {
        return this.tedadRoozInput.getAttribute('value');
    }

    async setMablaghHaghBimeInput(mablaghHaghBime) {
        await this.mablaghHaghBimeInput.sendKeys(mablaghHaghBime);
    }

    async getMablaghHaghBimeInput() {
        return this.mablaghHaghBimeInput.getAttribute('value');
    }

    async setTarikhShorooInput(tarikhShoroo) {
        await this.tarikhShorooInput.sendKeys(tarikhShoroo);
    }

    async getTarikhShorooInput() {
        return this.tarikhShorooInput.getAttribute('value');
    }

    async setTarikhPayanInput(tarikhPayan) {
        await this.tarikhPayanInput.sendKeys(tarikhPayan);
    }

    async getTarikhPayanInput() {
        return this.tarikhPayanInput.getAttribute('value');
    }

    async namesherkatSelectLastOption() {
        await this.namesherkatSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async namesherkatSelectOption(option) {
        await this.namesherkatSelect.sendKeys(option);
    }

    getNamesherkatSelect(): ElementFinder {
        return this.namesherkatSelect;
    }

    async getNamesherkatSelectedOption() {
        return this.namesherkatSelect.element(by.css('option:checked')).getText();
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

export class SalesSarneshinCalcDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-salesSarneshinCalc-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-salesSarneshinCalc'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
