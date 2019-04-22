/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SalesJaniCalcComponentsPage, SalesJaniCalcDeleteDialog, SalesJaniCalcUpdatePage } from './sales-jani-calc.page-object';

const expect = chai.expect;

describe('SalesJaniCalc e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let salesJaniCalcUpdatePage: SalesJaniCalcUpdatePage;
    let salesJaniCalcComponentsPage: SalesJaniCalcComponentsPage;
    /*let salesJaniCalcDeleteDialog: SalesJaniCalcDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SalesJaniCalcs', async () => {
        await navBarPage.goToEntity('sales-jani-calc');
        salesJaniCalcComponentsPage = new SalesJaniCalcComponentsPage();
        await browser.wait(ec.visibilityOf(salesJaniCalcComponentsPage.title), 5000);
        expect(await salesJaniCalcComponentsPage.getTitle()).to.eq('insurancestartApp.salesJaniCalc.home.title');
    });

    it('should load create SalesJaniCalc page', async () => {
        await salesJaniCalcComponentsPage.clickOnCreateButton();
        salesJaniCalcUpdatePage = new SalesJaniCalcUpdatePage();
        expect(await salesJaniCalcUpdatePage.getPageTitle()).to.eq('insurancestartApp.salesJaniCalc.home.createOrEditLabel');
        await salesJaniCalcUpdatePage.cancel();
    });

    /* it('should create and save SalesJaniCalcs', async () => {
        const nbButtonsBeforeCreate = await salesJaniCalcComponentsPage.countDeleteButtons();

        await salesJaniCalcComponentsPage.clickOnCreateButton();
        await promise.all([
            salesJaniCalcUpdatePage.setMablaghJaniInput('5'),
            salesJaniCalcUpdatePage.setMablaghMaliEjbariInput('5'),
            salesJaniCalcUpdatePage.setTedadRoozInput('5'),
            salesJaniCalcUpdatePage.setTarikhShoroFaaliatInput('2000-12-31'),
            salesJaniCalcUpdatePage.setTarikhPayanFaaliatInput('2000-12-31'),
            salesJaniCalcUpdatePage.setNaamSherkatInput('naamSherkat'),
            salesJaniCalcUpdatePage.setHaghbimeInput('5'),
            salesJaniCalcUpdatePage.bimenameSelectLastOption(),
            salesJaniCalcUpdatePage.grouhKhodroSelectLastOption(),
        ]);
        expect(await salesJaniCalcUpdatePage.getMablaghJaniInput()).to.eq('5');
        expect(await salesJaniCalcUpdatePage.getMablaghMaliEjbariInput()).to.eq('5');
        expect(await salesJaniCalcUpdatePage.getTedadRoozInput()).to.eq('5');
        expect(await salesJaniCalcUpdatePage.getTarikhShoroFaaliatInput()).to.eq('2000-12-31');
        expect(await salesJaniCalcUpdatePage.getTarikhPayanFaaliatInput()).to.eq('2000-12-31');
        expect(await salesJaniCalcUpdatePage.getNaamSherkatInput()).to.eq('naamSherkat');
        expect(await salesJaniCalcUpdatePage.getHaghbimeInput()).to.eq('5');
        await salesJaniCalcUpdatePage.save();
        expect(await salesJaniCalcUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await salesJaniCalcComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last SalesJaniCalc', async () => {
        const nbButtonsBeforeDelete = await salesJaniCalcComponentsPage.countDeleteButtons();
        await salesJaniCalcComponentsPage.clickOnLastDeleteButton();

        salesJaniCalcDeleteDialog = new SalesJaniCalcDeleteDialog();
        expect(await salesJaniCalcDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.salesJaniCalc.delete.question');
        await salesJaniCalcDeleteDialog.clickOnConfirmButton();

        expect(await salesJaniCalcComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
