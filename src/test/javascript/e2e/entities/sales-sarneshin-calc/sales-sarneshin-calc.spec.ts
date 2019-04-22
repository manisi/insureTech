/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    SalesSarneshinCalcComponentsPage,
    SalesSarneshinCalcDeleteDialog,
    SalesSarneshinCalcUpdatePage
} from './sales-sarneshin-calc.page-object';

const expect = chai.expect;

describe('SalesSarneshinCalc e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let salesSarneshinCalcUpdatePage: SalesSarneshinCalcUpdatePage;
    let salesSarneshinCalcComponentsPage: SalesSarneshinCalcComponentsPage;
    /*let salesSarneshinCalcDeleteDialog: SalesSarneshinCalcDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SalesSarneshinCalcs', async () => {
        await navBarPage.goToEntity('sales-sarneshin-calc');
        salesSarneshinCalcComponentsPage = new SalesSarneshinCalcComponentsPage();
        await browser.wait(ec.visibilityOf(salesSarneshinCalcComponentsPage.title), 5000);
        expect(await salesSarneshinCalcComponentsPage.getTitle()).to.eq('insurancestartApp.salesSarneshinCalc.home.title');
    });

    it('should load create SalesSarneshinCalc page', async () => {
        await salesSarneshinCalcComponentsPage.clickOnCreateButton();
        salesSarneshinCalcUpdatePage = new SalesSarneshinCalcUpdatePage();
        expect(await salesSarneshinCalcUpdatePage.getPageTitle()).to.eq('insurancestartApp.salesSarneshinCalc.home.createOrEditLabel');
        await salesSarneshinCalcUpdatePage.cancel();
    });

    /* it('should create and save SalesSarneshinCalcs', async () => {
        const nbButtonsBeforeCreate = await salesSarneshinCalcComponentsPage.countDeleteButtons();

        await salesSarneshinCalcComponentsPage.clickOnCreateButton();
        await promise.all([
            salesSarneshinCalcUpdatePage.setMablaghSalesMazadInput('5'),
            salesSarneshinCalcUpdatePage.setTedadRoozInput('5'),
            salesSarneshinCalcUpdatePage.setMablaghHaghBimeInput('5'),
            salesSarneshinCalcUpdatePage.setTarikhShorooInput('2000-12-31'),
            salesSarneshinCalcUpdatePage.setTarikhPayanInput('2000-12-31'),
            salesSarneshinCalcUpdatePage.namesherkatSelectLastOption(),
            salesSarneshinCalcUpdatePage.grouhKhodroSelectLastOption(),
        ]);
        expect(await salesSarneshinCalcUpdatePage.getMablaghSalesMazadInput()).to.eq('5');
        expect(await salesSarneshinCalcUpdatePage.getTedadRoozInput()).to.eq('5');
        expect(await salesSarneshinCalcUpdatePage.getMablaghHaghBimeInput()).to.eq('5');
        expect(await salesSarneshinCalcUpdatePage.getTarikhShorooInput()).to.eq('2000-12-31');
        expect(await salesSarneshinCalcUpdatePage.getTarikhPayanInput()).to.eq('2000-12-31');
        await salesSarneshinCalcUpdatePage.save();
        expect(await salesSarneshinCalcUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await salesSarneshinCalcComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last SalesSarneshinCalc', async () => {
        const nbButtonsBeforeDelete = await salesSarneshinCalcComponentsPage.countDeleteButtons();
        await salesSarneshinCalcComponentsPage.clickOnLastDeleteButton();

        salesSarneshinCalcDeleteDialog = new SalesSarneshinCalcDeleteDialog();
        expect(await salesSarneshinCalcDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.salesSarneshinCalc.delete.question');
        await salesSarneshinCalcDeleteDialog.clickOnConfirmButton();

        expect(await salesSarneshinCalcComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
