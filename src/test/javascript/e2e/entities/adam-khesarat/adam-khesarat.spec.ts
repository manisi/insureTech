/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AdamKhesaratComponentsPage, AdamKhesaratDeleteDialog, AdamKhesaratUpdatePage } from './adam-khesarat.page-object';

const expect = chai.expect;

describe('AdamKhesarat e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let adamKhesaratUpdatePage: AdamKhesaratUpdatePage;
    let adamKhesaratComponentsPage: AdamKhesaratComponentsPage;
    /*let adamKhesaratDeleteDialog: AdamKhesaratDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load AdamKhesarats', async () => {
        await navBarPage.goToEntity('adam-khesarat');
        adamKhesaratComponentsPage = new AdamKhesaratComponentsPage();
        expect(await adamKhesaratComponentsPage.getTitle()).to.eq('insurancestartApp.adamKhesarat.home.title');
    });

    it('should load create AdamKhesarat page', async () => {
        await adamKhesaratComponentsPage.clickOnCreateButton();
        adamKhesaratUpdatePage = new AdamKhesaratUpdatePage();
        expect(await adamKhesaratUpdatePage.getPageTitle()).to.eq('insurancestartApp.adamKhesarat.home.createOrEditLabel');
        await adamKhesaratUpdatePage.cancel();
    });

    /* it('should create and save AdamKhesarats', async () => {
        const nbButtonsBeforeCreate = await adamKhesaratComponentsPage.countDeleteButtons();

        await adamKhesaratComponentsPage.clickOnCreateButton();
        await promise.all([
            adamKhesaratUpdatePage.setSalesInput('5'),
            adamKhesaratUpdatePage.setMazadInput('5'),
            adamKhesaratUpdatePage.setSarneshinInput('5'),
            adamKhesaratUpdatePage.sabegheSelectLastOption(),
            adamKhesaratUpdatePage.noeSabegheSelectLastOption(),
        ]);
        expect(await adamKhesaratUpdatePage.getSalesInput()).to.eq('5');
        expect(await adamKhesaratUpdatePage.getMazadInput()).to.eq('5');
        expect(await adamKhesaratUpdatePage.getSarneshinInput()).to.eq('5');
        const selectedFaal = adamKhesaratUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await adamKhesaratUpdatePage.getFaalInput().click();
            expect(await adamKhesaratUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await adamKhesaratUpdatePage.getFaalInput().click();
            expect(await adamKhesaratUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await adamKhesaratUpdatePage.save();
        expect(await adamKhesaratUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await adamKhesaratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last AdamKhesarat', async () => {
        const nbButtonsBeforeDelete = await adamKhesaratComponentsPage.countDeleteButtons();
        await adamKhesaratComponentsPage.clickOnLastDeleteButton();

        adamKhesaratDeleteDialog = new AdamKhesaratDeleteDialog();
        expect(await adamKhesaratDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.adamKhesarat.delete.question');
        await adamKhesaratDeleteDialog.clickOnConfirmButton();

        expect(await adamKhesaratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
