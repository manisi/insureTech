import { element, by, ElementFinder } from 'protractor';

export class MohasebeBadaneComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('insutech-mohasebe-badane div table .btn-danger'));
    title = element.all(by.css('insutech-mohasebe-badane div h2#page-heading span')).first();

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

export class MohasebeBadaneUpdatePage {
    pageTitle = element(by.id('insutech-mohasebe-badane-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameSherkatInput = element(by.id('field_nameSherkat'));
    saltakhfifInput = element(by.id('field_saltakhfif'));
    tipsSelect = element(by.id('field_tips'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNameSherkatInput(nameSherkat) {
        await this.nameSherkatInput.sendKeys(nameSherkat);
    }

    async getNameSherkatInput() {
        return this.nameSherkatInput.getAttribute('value');
    }

    async setSaltakhfifInput(saltakhfif) {
        await this.saltakhfifInput.sendKeys(saltakhfif);
    }

    async getSaltakhfifInput() {
        return this.saltakhfifInput.getAttribute('value');
    }

    async tipsSelectLastOption() {
        await this.tipsSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async tipsSelectOption(option) {
        await this.tipsSelect.sendKeys(option);
    }

    getTipsSelect(): ElementFinder {
        return this.tipsSelect;
    }

    async getTipsSelectedOption() {
        return this.tipsSelect.element(by.css('option:checked')).getText();
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

export class MohasebeBadaneDeleteDialog {
    private dialogTitle = element(by.id('insutech-delete-mohasebeBadane-heading'));
    private confirmButton = element(by.id('insutech-confirm-delete-mohasebeBadane'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
