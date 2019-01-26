/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    AdamKhesaratBadaneComponentsPage,
    AdamKhesaratBadaneDeleteDialog,
    AdamKhesaratBadaneUpdatePage
} from './adam-khesarat-badane.page-object';

const expect = chai.expect;

describe('AdamKhesaratBadane e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let adamKhesaratBadaneUpdatePage: AdamKhesaratBadaneUpdatePage;
    let adamKhesaratBadaneComponentsPage: AdamKhesaratBadaneComponentsPage;
    /*let adamKhesaratBadaneDeleteDialog: AdamKhesaratBadaneDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load AdamKhesaratBadanes', async () => {
        await navBarPage.goToEntity('adam-khesarat-badane');
        adamKhesaratBadaneComponentsPage = new AdamKhesaratBadaneComponentsPage();
        await browser.wait(ec.visibilityOf(adamKhesaratBadaneComponentsPage.title), 5000);
        expect(await adamKhesaratBadaneComponentsPage.getTitle()).to.eq('insurancestartApp.adamKhesaratBadane.home.title');
    });

    it('should load create AdamKhesaratBadane page', async () => {
        await adamKhesaratBadaneComponentsPage.clickOnCreateButton();
        adamKhesaratBadaneUpdatePage = new AdamKhesaratBadaneUpdatePage();
        expect(await adamKhesaratBadaneUpdatePage.getPageTitle()).to.eq('insurancestartApp.adamKhesaratBadane.home.createOrEditLabel');
        await adamKhesaratBadaneUpdatePage.cancel();
    });

    /* it('should create and save AdamKhesaratBadanes', async () => {
        const nbButtonsBeforeCreate = await adamKhesaratBadaneComponentsPage.countDeleteButtons();

        await adamKhesaratBadaneComponentsPage.clickOnCreateButton();
        await promise.all([
            adamKhesaratBadaneUpdatePage.setBadaneInput('5'),
            adamKhesaratBadaneUpdatePage.noeSabegheSelectLastOption(),
            adamKhesaratBadaneUpdatePage.sabegheSelectLastOption(),
        ]);
        expect(await adamKhesaratBadaneUpdatePage.getBadaneInput()).to.eq('5');
        const selectedFaal = adamKhesaratBadaneUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await adamKhesaratBadaneUpdatePage.getFaalInput().click();
            expect(await adamKhesaratBadaneUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await adamKhesaratBadaneUpdatePage.getFaalInput().click();
            expect(await adamKhesaratBadaneUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await adamKhesaratBadaneUpdatePage.save();
        expect(await adamKhesaratBadaneUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await adamKhesaratBadaneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last AdamKhesaratBadane', async () => {
        const nbButtonsBeforeDelete = await adamKhesaratBadaneComponentsPage.countDeleteButtons();
        await adamKhesaratBadaneComponentsPage.clickOnLastDeleteButton();

        adamKhesaratBadaneDeleteDialog = new AdamKhesaratBadaneDeleteDialog();
        expect(await adamKhesaratBadaneDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.adamKhesaratBadane.delete.question');
        await adamKhesaratBadaneDeleteDialog.clickOnConfirmButton();

        expect(await adamKhesaratBadaneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
