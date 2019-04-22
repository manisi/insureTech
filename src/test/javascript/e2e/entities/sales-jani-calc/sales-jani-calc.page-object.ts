import { element, by, ElementFinder } from 'protractor';

export class SalesJaniCalcComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-sales-jani-calc div table .btn-danger'));
    title = element.all(by.css('jhi-sales-jani-calc div h2#page-heading span')).first();

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

export class SalesJaniCalcUpdatePage {
    pageTitle = element(by.id('jhi-sales-jani-calc-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    mablaghJaniInput = element(by.id('field_mablaghJani'));
    mablaghMaliEjbariInput = element(by.id('field_mablaghMaliEjbari'));
    tedadRoozInput = element(by.id('field_tedadRooz'));
    tarikhShoroFaaliatInput = element(by.id('field_tarikhShoroFaaliat'));
    tarikhPayanFaaliatInput = element(by.id('field_tarikhPayanFaaliat'));
    naamSherkatInput = element(by.id('field_naamSherkat'));
    haghbimeInput = element(by.id('field_haghbime'));
    bimenameSelect = element(by.id('field_bimename'));
    grouhKhodroSelect = element(by.id('field_grouhKhodro'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setMablaghJaniInput(mablaghJani) {
        await this.mablaghJaniInput.sendKeys(mablaghJani);
    }

    async getMablaghJaniInput() {
        return this.mablaghJaniInput.getAttribute('value');
    }

    async setMablaghMaliEjbariInput(mablaghMaliEjbari) {
        await this.mablaghMaliEjbariInput.sendKeys(mablaghMaliEjbari);
    }

    async getMablaghMaliEjbariInput() {
        return this.mablaghMaliEjbariInput.getAttribute('value');
    }

    async setTedadRoozInput(tedadRooz) {
        await this.tedadRoozInput.sendKeys(tedadRooz);
    }

    async getTedadRoozInput() {
        return this.tedadRoozInput.getAttribute('value');
    }

    async setTarikhShoroFaaliatInput(tarikhShoroFaaliat) {
        await this.tarikhShoroFaaliatInput.sendKeys(tarikhShoroFaaliat);
    }

    async getTarikhShoroFaaliatInput() {
        return this.tarikhShoroFaaliatInput.getAttribute('value');
    }

    async setTarikhPayanFaaliatInput(tarikhPayanFaaliat) {
        await this.tarikhPayanFaaliatInput.sendKeys(tarikhPayanFaaliat);
    }

    async getTarikhPayanFaaliatInput() {
        return this.tarikhPayanFaaliatInput.getAttribute('value');
    }

    async setNaamSherkatInput(naamSherkat) {
        await this.naamSherkatInput.sendKeys(naamSherkat);
    }

    async getNaamSherkatInput() {
        return this.naamSherkatInput.getAttribute('value');
    }

    async setHaghbimeInput(haghbime) {
        await this.haghbimeInput.sendKeys(haghbime);
    }

    async getHaghbimeInput() {
        return this.haghbimeInput.getAttribute('value');
    }

    async bimenameSelectLastOption() {
        await this.bimenameSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async bimenameSelectOption(option) {
        await this.bimenameSelect.sendKeys(option);
    }

    getBimenameSelect(): ElementFinder {
        return this.bimenameSelect;
    }

    async getBimenameSelectedOption() {
        return this.bimenameSelect.element(by.css('option:checked')).getText();
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

export class SalesJaniCalcDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-salesJaniCalc-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-salesJaniCalc'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
