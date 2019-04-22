/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SalesMazadCalcComponentsPage, SalesMazadCalcDeleteDialog, SalesMazadCalcUpdatePage } from './sales-mazad-calc.page-object';

const expect = chai.expect;

describe('SalesMazadCalc e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let salesMazadCalcUpdatePage: SalesMazadCalcUpdatePage;
    let salesMazadCalcComponentsPage: SalesMazadCalcComponentsPage;
    /*let salesMazadCalcDeleteDialog: SalesMazadCalcDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SalesMazadCalcs', async () => {
        await navBarPage.goToEntity('sales-mazad-calc');
        salesMazadCalcComponentsPage = new SalesMazadCalcComponentsPage();
        await browser.wait(ec.visibilityOf(salesMazadCalcComponentsPage.title), 5000);
        expect(await salesMazadCalcComponentsPage.getTitle()).to.eq('insurancestartApp.salesMazadCalc.home.title');
    });

    it('should load create SalesMazadCalc page', async () => {
        await salesMazadCalcComponentsPage.clickOnCreateButton();
        salesMazadCalcUpdatePage = new SalesMazadCalcUpdatePage();
        expect(await salesMazadCalcUpdatePage.getPageTitle()).to.eq('insurancestartApp.salesMazadCalc.home.createOrEditLabel');
        await salesMazadCalcUpdatePage.cancel();
    });

    /* it('should create and save SalesMazadCalcs', async () => {
        const nbButtonsBeforeCreate = await salesMazadCalcComponentsPage.countDeleteButtons();

        await salesMazadCalcComponentsPage.clickOnCreateButton();
        await promise.all([
            salesMazadCalcUpdatePage.setMablaghSalesMazadInput('5'),
            salesMazadCalcUpdatePage.setTedadRoozInput('5'),
            salesMazadCalcUpdatePage.setHaghBimeInput('5'),
            salesMazadCalcUpdatePage.setTarikhShorooInput('2000-12-31'),
            salesMazadCalcUpdatePage.setTarikhPayanInput('2000-12-31'),
            salesMazadCalcUpdatePage.namesherkatSelectLastOption(),
            salesMazadCalcUpdatePage.grouhKhodroSelectLastOption(),
        ]);
        expect(await salesMazadCalcUpdatePage.getMablaghSalesMazadInput()).to.eq('5');
        expect(await salesMazadCalcUpdatePage.getTedadRoozInput()).to.eq('5');
        expect(await salesMazadCalcUpdatePage.getHaghBimeInput()).to.eq('5');
        expect(await salesMazadCalcUpdatePage.getTarikhShorooInput()).to.eq('2000-12-31');
        expect(await salesMazadCalcUpdatePage.getTarikhPayanInput()).to.eq('2000-12-31');
        await salesMazadCalcUpdatePage.save();
        expect(await salesMazadCalcUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await salesMazadCalcComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last SalesMazadCalc', async () => {
        const nbButtonsBeforeDelete = await salesMazadCalcComponentsPage.countDeleteButtons();
        await salesMazadCalcComponentsPage.clickOnLastDeleteButton();

        salesMazadCalcDeleteDialog = new SalesMazadCalcDeleteDialog();
        expect(await salesMazadCalcDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.salesMazadCalc.delete.question');
        await salesMazadCalcDeleteDialog.clickOnConfirmButton();

        expect(await salesMazadCalcComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
