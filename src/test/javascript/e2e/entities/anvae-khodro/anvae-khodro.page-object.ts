import { element, by, ElementFinder } from 'protractor';

export class AnvaeKhodroComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-anvae-khodro div table .btn-danger'));
    title = element.all(by.css('jhi-anvae-khodro div h2#page-heading span')).first();

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

export class AnvaeKhodroUpdatePage {
    pageTitle = element(by.id('jhi-anvae-khodro-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    grouhVasileInput = element(by.id('field_grouhVasile'));
    systemVasileInput = element(by.id('field_systemVasile'));
    onvanInput = element(by.id('field_onvan'));
    tonazhInput = element(by.id('field_tonazh'));
    tedadSarneshinInput = element(by.id('field_tedadSarneshin'));
    tedadSilandreInput = element(by.id('field_tedadSilandre'));
    dasteBandiInput = element(by.id('field_dasteBandi'));
    savariTypeInput = element(by.id('field_savariType'));
    grouhKhodroSelect = element(by.id('field_grouhKhodro'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setGrouhVasileInput(grouhVasile) {
        await this.grouhVasileInput.sendKeys(grouhVasile);
    }

    async getGrouhVasileInput() {
        return this.grouhVasileInput.getAttribute('value');
    }

    async setSystemVasileInput(systemVasile) {
        await this.systemVasileInput.sendKeys(systemVasile);
    }

    async getSystemVasileInput() {
        return this.systemVasileInput.getAttribute('value');
    }

    async setOnvanInput(onvan) {
        await this.onvanInput.sendKeys(onvan);
    }

    async getOnvanInput() {
        return this.onvanInput.getAttribute('value');
    }

    async setTonazhInput(tonazh) {
        await this.tonazhInput.sendKeys(tonazh);
    }

    async getTonazhInput() {
        return this.tonazhInput.getAttribute('value');
    }

    async setTedadSarneshinInput(tedadSarneshin) {
        await this.tedadSarneshinInput.sendKeys(tedadSarneshin);
    }

    async getTedadSarneshinInput() {
        return this.tedadSarneshinInput.getAttribute('value');
    }

    async setTedadSilandreInput(tedadSilandre) {
        await this.tedadSilandreInput.sendKeys(tedadSilandre);
    }

    async getTedadSilandreInput() {
        return this.tedadSilandreInput.getAttribute('value');
    }

    async setDasteBandiInput(dasteBandi) {
        await this.dasteBandiInput.sendKeys(dasteBandi);
    }

    async getDasteBandiInput() {
        return this.dasteBandiInput.getAttribute('value');
    }

    async setSavariTypeInput(savariType) {
        await this.savariTypeInput.sendKeys(savariType);
    }

    async getSavariTypeInput() {
        return this.savariTypeInput.getAttribute('value');
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

export class AnvaeKhodroDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-anvaeKhodro-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-anvaeKhodro'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
