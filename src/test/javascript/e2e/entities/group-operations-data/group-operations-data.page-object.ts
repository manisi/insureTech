import { element, by, ElementFinder } from 'protractor';

export class GroupOperationsDataComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-group-operations-data div table .btn-danger'));
    title = element.all(by.css('jhi-group-operations-data div h2#page-heading span')).first();

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

export class GroupOperationsDataUpdatePage {
    pageTitle = element(by.id('jhi-group-operations-data-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    entityNameSelect = element(by.id('field_entityName'));
    actingInput = element(by.id('field_acting'));
    estateSelect = element(by.id('field_estate'));
    uploadfileInput = element(by.id('file_uploadfile'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setEntityNameSelect(entityName) {
        await this.entityNameSelect.sendKeys(entityName);
    }

    async getEntityNameSelect() {
        return this.entityNameSelect.element(by.css('option:checked')).getText();
    }

    async entityNameSelectLastOption() {
        await this.entityNameSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    getActingInput() {
        return this.actingInput;
    }
    async setEstateSelect(estate) {
        await this.estateSelect.sendKeys(estate);
    }

    async getEstateSelect() {
        return this.estateSelect.element(by.css('option:checked')).getText();
    }

    async estateSelectLastOption() {
        await this.estateSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setUploadfileInput(uploadfile) {
        await this.uploadfileInput.sendKeys(uploadfile);
    }

    async getUploadfileInput() {
        return this.uploadfileInput.getAttribute('value');
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

export class GroupOperationsDataDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-groupOperationsData-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-groupOperationsData'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
