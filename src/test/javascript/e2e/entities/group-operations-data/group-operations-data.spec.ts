/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    GroupOperationsDataComponentsPage,
    GroupOperationsDataDeleteDialog,
    GroupOperationsDataUpdatePage
} from './group-operations-data.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('GroupOperationsData e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let groupOperationsDataUpdatePage: GroupOperationsDataUpdatePage;
    let groupOperationsDataComponentsPage: GroupOperationsDataComponentsPage;
    let groupOperationsDataDeleteDialog: GroupOperationsDataDeleteDialog;
    const fileNameToUpload = 'logo-jhipster.png';
    const fileToUpload = '../../../../../main/webapp/content/images/' + fileNameToUpload;
    const absolutePath = path.resolve(__dirname, fileToUpload);

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load GroupOperationsData', async () => {
        await navBarPage.goToEntity('group-operations-data');
        groupOperationsDataComponentsPage = new GroupOperationsDataComponentsPage();
        await browser.wait(ec.visibilityOf(groupOperationsDataComponentsPage.title), 5000);
        expect(await groupOperationsDataComponentsPage.getTitle()).to.eq('insurancestartApp.groupOperationsData.home.title');
    });

    it('should load create GroupOperationsData page', async () => {
        await groupOperationsDataComponentsPage.clickOnCreateButton();
        groupOperationsDataUpdatePage = new GroupOperationsDataUpdatePage();
        expect(await groupOperationsDataUpdatePage.getPageTitle()).to.eq('insurancestartApp.groupOperationsData.home.createOrEditLabel');
        await groupOperationsDataUpdatePage.cancel();
    });

    it('should create and save GroupOperationsData', async () => {
        const nbButtonsBeforeCreate = await groupOperationsDataComponentsPage.countDeleteButtons();

        await groupOperationsDataComponentsPage.clickOnCreateButton();
        await promise.all([
            groupOperationsDataUpdatePage.entityNameSelectLastOption(),
            groupOperationsDataUpdatePage.estateSelectLastOption(),
            groupOperationsDataUpdatePage.setUploadfileInput(absolutePath)
        ]);
        const selectedActing = groupOperationsDataUpdatePage.getActingInput();
        if (await selectedActing.isSelected()) {
            await groupOperationsDataUpdatePage.getActingInput().click();
            expect(await groupOperationsDataUpdatePage.getActingInput().isSelected()).to.be.false;
        } else {
            await groupOperationsDataUpdatePage.getActingInput().click();
            expect(await groupOperationsDataUpdatePage.getActingInput().isSelected()).to.be.true;
        }
        expect(await groupOperationsDataUpdatePage.getUploadfileInput()).to.endsWith(fileNameToUpload);
        await groupOperationsDataUpdatePage.save();
        expect(await groupOperationsDataUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await groupOperationsDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last GroupOperationsData', async () => {
        const nbButtonsBeforeDelete = await groupOperationsDataComponentsPage.countDeleteButtons();
        await groupOperationsDataComponentsPage.clickOnLastDeleteButton();

        groupOperationsDataDeleteDialog = new GroupOperationsDataDeleteDialog();
        expect(await groupOperationsDataDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.groupOperationsData.delete.question');
        await groupOperationsDataDeleteDialog.clickOnConfirmButton();

        expect(await groupOperationsDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
