import { element, by, ElementFinder } from 'protractor';

export class EstelaamSalesNerkhComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-estelaam-sales-nerkh div table .btn-danger'));
    title = element.all(by.css('jhi-estelaam-sales-nerkh div h2#page-heading span')).first();

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

export class EstelaamSalesNerkhUpdatePage {
    pageTitle = element(by.id('jhi-estelaam-sales-nerkh-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    anvaeKhodroSelect = element(by.id('field_anvaeKhodro'));
    saalSakhtSelect = element(by.id('field_saalSakht'));
    onvanKhodroSelect = element(by.id('field_onvanKhodro'));
    adamKhesaratSelect = element(by.id('field_adamKhesarat'));
    adamKhesaratSarneshinSelect = element(by.id('field_adamKhesaratSarneshin'));
    khesaratSrneshinSelect = element(by.id('field_khesaratSrneshin'));
    khesaratSalesSelect = element(by.id('field_khesaratSales'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async anvaeKhodroSelectLastOption() {
        await this.anvaeKhodroSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async anvaeKhodroSelectOption(option) {
        await this.anvaeKhodroSelect.sendKeys(option);
    }

    getAnvaeKhodroSelect(): ElementFinder {
        return this.anvaeKhodroSelect;
    }

    async getAnvaeKhodroSelectedOption() {
        return await this.anvaeKhodroSelect.element(by.css('option:checked')).getText();
    }

    async saalSakhtSelectLastOption() {
        await this.saalSakhtSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async saalSakhtSelectOption(option) {
        await this.saalSakhtSelect.sendKeys(option);
    }

    getSaalSakhtSelect(): ElementFinder {
        return this.saalSakhtSelect;
    }

    async getSaalSakhtSelectedOption() {
        return await this.saalSakhtSelect.element(by.css('option:checked')).getText();
    }

    async onvanKhodroSelectLastOption() {
        await this.onvanKhodroSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async onvanKhodroSelectOption(option) {
        await this.onvanKhodroSelect.sendKeys(option);
    }

    getOnvanKhodroSelect(): ElementFinder {
        return this.onvanKhodroSelect;
    }

    async getOnvanKhodroSelectedOption() {
        return await this.onvanKhodroSelect.element(by.css('option:checked')).getText();
    }

    async adamKhesaratSelectLastOption() {
        await this.adamKhesaratSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async adamKhesaratSelectOption(option) {
        await this.adamKhesaratSelect.sendKeys(option);
    }

    getAdamKhesaratSelect(): ElementFinder {
        return this.adamKhesaratSelect;
    }

    async getAdamKhesaratSelectedOption() {
        return await this.adamKhesaratSelect.element(by.css('option:checked')).getText();
    }

    async adamKhesaratSarneshinSelectLastOption() {
        await this.adamKhesaratSarneshinSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async adamKhesaratSarneshinSelectOption(option) {
        await this.adamKhesaratSarneshinSelect.sendKeys(option);
    }

    getAdamKhesaratSarneshinSelect(): ElementFinder {
        return this.adamKhesaratSarneshinSelect;
    }

    async getAdamKhesaratSarneshinSelectedOption() {
        return await this.adamKhesaratSarneshinSelect.element(by.css('option:checked')).getText();
    }

    async khesaratSrneshinSelectLastOption() {
        await this.khesaratSrneshinSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async khesaratSrneshinSelectOption(option) {
        await this.khesaratSrneshinSelect.sendKeys(option);
    }

    getKhesaratSrneshinSelect(): ElementFinder {
        return this.khesaratSrneshinSelect;
    }

    async getKhesaratSrneshinSelectedOption() {
        return await this.khesaratSrneshinSelect.element(by.css('option:checked')).getText();
    }

    async khesaratSalesSelectLastOption() {
        await this.khesaratSalesSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async khesaratSalesSelectOption(option) {
        await this.khesaratSalesSelect.sendKeys(option);
    }

    getKhesaratSalesSelect(): ElementFinder {
        return this.khesaratSalesSelect;
    }

    async getKhesaratSalesSelectedOption() {
        return await this.khesaratSalesSelect.element(by.css('option:checked')).getText();
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

export class EstelaamSalesNerkhDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-estelaamSalesNerkh-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-estelaamSalesNerkh'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
