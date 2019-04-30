import { element, by, ElementFinder } from 'protractor';

export class MoredEstefadeSalesComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-mored-estefade-sales div table .btn-danger'));
    title = element.all(by.css('jhi-mored-estefade-sales div h2#page-heading span')).first();

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

export class MoredEstefadeSalesUpdatePage {
    pageTitle = element(by.id('jhi-mored-estefade-sales-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    darsadEzafeNerkhInput = element(by.id('field_darsadEzafeNerkh'));
    azTarikhInput = element(by.id('field_azTarikh'));
    taTarikhInput = element(by.id('field_taTarikh'));
    faalInput = element(by.id('field_faal'));
    grouhKhodroSelect = element(by.id('field_grouhKhodro'));
    sherkatBimeSelect = element(by.id('field_sherkatBime'));
    onvanKhodroSelect = element(by.id('field_onvanKhodro'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDarsadEzafeNerkhInput(darsadEzafeNerkh) {
        await this.darsadEzafeNerkhInput.sendKeys(darsadEzafeNerkh);
    }

    async getDarsadEzafeNerkhInput() {
        return this.darsadEzafeNerkhInput.getAttribute('value');
    }

    async setAzTarikhInput(azTarikh) {
        await this.azTarikhInput.sendKeys(azTarikh);
    }

    async getAzTarikhInput() {
        return this.azTarikhInput.getAttribute('value');
    }

    async setTaTarikhInput(taTarikh) {
        await this.taTarikhInput.sendKeys(taTarikh);
    }

    async getTaTarikhInput() {
        return this.taTarikhInput.getAttribute('value');
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
        return this.onvanKhodroSelect.element(by.css('option:checked')).getText();
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

export class MoredEstefadeSalesDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-moredEstefadeSales-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-moredEstefadeSales'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
