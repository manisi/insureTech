/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OnvanKhodroComponentsPage, OnvanKhodroDeleteDialog, OnvanKhodroUpdatePage } from './onvan-khodro.page-object';

const expect = chai.expect;

describe('OnvanKhodro e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let onvanKhodroUpdatePage: OnvanKhodroUpdatePage;
    let onvanKhodroComponentsPage: OnvanKhodroComponentsPage;
    let onvanKhodroDeleteDialog: OnvanKhodroDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load OnvanKhodros', async () => {
        await navBarPage.goToEntity('onvan-khodro');
        onvanKhodroComponentsPage = new OnvanKhodroComponentsPage();
        await browser.wait(ec.visibilityOf(onvanKhodroComponentsPage.title), 5000);
        expect(await onvanKhodroComponentsPage.getTitle()).to.eq('insurancestartApp.onvanKhodro.home.title');
    });

    it('should load create OnvanKhodro page', async () => {
        await onvanKhodroComponentsPage.clickOnCreateButton();
        onvanKhodroUpdatePage = new OnvanKhodroUpdatePage();
        expect(await onvanKhodroUpdatePage.getPageTitle()).to.eq('insurancestartApp.onvanKhodro.home.createOrEditLabel');
        await onvanKhodroUpdatePage.cancel();
    });

    it('should create and save OnvanKhodros', async () => {
        const nbButtonsBeforeCreate = await onvanKhodroComponentsPage.countDeleteButtons();

        await onvanKhodroComponentsPage.clickOnCreateButton();
        await promise.all([
            onvanKhodroUpdatePage.setNameInput('name'),
            onvanKhodroUpdatePage.setSharhInput('sharh'),
            onvanKhodroUpdatePage.setAzTarikhInput('2000-12-31'),
            onvanKhodroUpdatePage.setTaTarikhInput('2000-12-31')
        ]);
        expect(await onvanKhodroUpdatePage.getNameInput()).to.eq('name');
        expect(await onvanKhodroUpdatePage.getSharhInput()).to.eq('sharh');
        expect(await onvanKhodroUpdatePage.getAzTarikhInput()).to.eq('2000-12-31');
        expect(await onvanKhodroUpdatePage.getTaTarikhInput()).to.eq('2000-12-31');
        const selectedFaal = onvanKhodroUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await onvanKhodroUpdatePage.getFaalInput().click();
            expect(await onvanKhodroUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await onvanKhodroUpdatePage.getFaalInput().click();
            expect(await onvanKhodroUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await onvanKhodroUpdatePage.save();
        expect(await onvanKhodroUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await onvanKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last OnvanKhodro', async () => {
        const nbButtonsBeforeDelete = await onvanKhodroComponentsPage.countDeleteButtons();
        await onvanKhodroComponentsPage.clickOnLastDeleteButton();

        onvanKhodroDeleteDialog = new OnvanKhodroDeleteDialog();
        expect(await onvanKhodroDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.onvanKhodro.delete.question');
        await onvanKhodroDeleteDialog.clickOnConfirmButton();

        expect(await onvanKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
