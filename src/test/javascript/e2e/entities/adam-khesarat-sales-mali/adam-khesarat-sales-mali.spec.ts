/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    AdamKhesaratSalesMaliComponentsPage,
    AdamKhesaratSalesMaliDeleteDialog,
    AdamKhesaratSalesMaliUpdatePage
} from './adam-khesarat-sales-mali.page-object';

const expect = chai.expect;

describe('AdamKhesaratSalesMali e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let adamKhesaratSalesMaliUpdatePage: AdamKhesaratSalesMaliUpdatePage;
    let adamKhesaratSalesMaliComponentsPage: AdamKhesaratSalesMaliComponentsPage;
    /*let adamKhesaratSalesMaliDeleteDialog: AdamKhesaratSalesMaliDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load AdamKhesaratSalesMalis', async () => {
        await navBarPage.goToEntity('adam-khesarat-sales-mali');
        adamKhesaratSalesMaliComponentsPage = new AdamKhesaratSalesMaliComponentsPage();
        await browser.wait(ec.visibilityOf(adamKhesaratSalesMaliComponentsPage.title), 5000);
        expect(await adamKhesaratSalesMaliComponentsPage.getTitle()).to.eq('insurancestartApp.adamKhesaratSalesMali.home.title');
    });

    it('should load create AdamKhesaratSalesMali page', async () => {
        await adamKhesaratSalesMaliComponentsPage.clickOnCreateButton();
        adamKhesaratSalesMaliUpdatePage = new AdamKhesaratSalesMaliUpdatePage();
        expect(await adamKhesaratSalesMaliUpdatePage.getPageTitle()).to.eq(
            'insurancestartApp.adamKhesaratSalesMali.home.createOrEditLabel'
        );
        await adamKhesaratSalesMaliUpdatePage.cancel();
    });

    /* it('should create and save AdamKhesaratSalesMalis', async () => {
        const nbButtonsBeforeCreate = await adamKhesaratSalesMaliComponentsPage.countDeleteButtons();

        await adamKhesaratSalesMaliComponentsPage.clickOnCreateButton();
        await promise.all([
            adamKhesaratSalesMaliUpdatePage.setSalesMaliInput('5'),
            adamKhesaratSalesMaliUpdatePage.sabegheSelectLastOption(),
            adamKhesaratSalesMaliUpdatePage.noeSabegheSelectLastOption(),
        ]);
        expect(await adamKhesaratSalesMaliUpdatePage.getSalesMaliInput()).to.eq('5');
        const selectedFaal = adamKhesaratSalesMaliUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await adamKhesaratSalesMaliUpdatePage.getFaalInput().click();
            expect(await adamKhesaratSalesMaliUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await adamKhesaratSalesMaliUpdatePage.getFaalInput().click();
            expect(await adamKhesaratSalesMaliUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await adamKhesaratSalesMaliUpdatePage.save();
        expect(await adamKhesaratSalesMaliUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await adamKhesaratSalesMaliComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last AdamKhesaratSalesMali', async () => {
        const nbButtonsBeforeDelete = await adamKhesaratSalesMaliComponentsPage.countDeleteButtons();
        await adamKhesaratSalesMaliComponentsPage.clickOnLastDeleteButton();

        adamKhesaratSalesMaliDeleteDialog = new AdamKhesaratSalesMaliDeleteDialog();
        expect(await adamKhesaratSalesMaliDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.adamKhesaratSalesMali.delete.question');
        await adamKhesaratSalesMaliDeleteDialog.clickOnConfirmButton();

        expect(await adamKhesaratSalesMaliComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
