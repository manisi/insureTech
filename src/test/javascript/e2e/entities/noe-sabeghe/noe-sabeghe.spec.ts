/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NoeSabegheComponentsPage, NoeSabegheDeleteDialog, NoeSabegheUpdatePage } from './noe-sabeghe.page-object';

const expect = chai.expect;

describe('NoeSabeghe e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let noeSabegheUpdatePage: NoeSabegheUpdatePage;
    let noeSabegheComponentsPage: NoeSabegheComponentsPage;
    let noeSabegheDeleteDialog: NoeSabegheDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load NoeSabeghes', async () => {
        await navBarPage.goToEntity('noe-sabeghe');
        noeSabegheComponentsPage = new NoeSabegheComponentsPage();
        expect(await noeSabegheComponentsPage.getTitle()).to.eq('insurancestartApp.noeSabeghe.home.title');
    });

    it('should load create NoeSabeghe page', async () => {
        await noeSabegheComponentsPage.clickOnCreateButton();
        noeSabegheUpdatePage = new NoeSabegheUpdatePage();
        expect(await noeSabegheUpdatePage.getPageTitle()).to.eq('insurancestartApp.noeSabeghe.home.createOrEditLabel');
        await noeSabegheUpdatePage.cancel();
    });

    it('should create and save NoeSabeghes', async () => {
        const nbButtonsBeforeCreate = await noeSabegheComponentsPage.countDeleteButtons();

        await noeSabegheComponentsPage.clickOnCreateButton();
        await promise.all([noeSabegheUpdatePage.setNameInput('name'), noeSabegheUpdatePage.setSharhInput('sharh')]);
        expect(await noeSabegheUpdatePage.getNameInput()).to.eq('name');
        expect(await noeSabegheUpdatePage.getSharhInput()).to.eq('sharh');
        const selectedFaal = noeSabegheUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await noeSabegheUpdatePage.getFaalInput().click();
            expect(await noeSabegheUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await noeSabegheUpdatePage.getFaalInput().click();
            expect(await noeSabegheUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await noeSabegheUpdatePage.save();
        expect(await noeSabegheUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await noeSabegheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last NoeSabeghe', async () => {
        const nbButtonsBeforeDelete = await noeSabegheComponentsPage.countDeleteButtons();
        await noeSabegheComponentsPage.clickOnLastDeleteButton();

        noeSabegheDeleteDialog = new NoeSabegheDeleteDialog();
        expect(await noeSabegheDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.noeSabeghe.delete.question');
        await noeSabegheDeleteDialog.clickOnConfirmButton();

        expect(await noeSabegheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
