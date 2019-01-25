/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MohasebeSalesComponentsPage, MohasebeSalesDeleteDialog, MohasebeSalesUpdatePage } from './mohasebe-sales.page-object';

const expect = chai.expect;

describe('MohasebeSales e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let mohasebeSalesUpdatePage: MohasebeSalesUpdatePage;
    let mohasebeSalesComponentsPage: MohasebeSalesComponentsPage;
    let mohasebeSalesDeleteDialog: MohasebeSalesDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load MohasebeSales', async () => {
        await navBarPage.goToEntity('mohasebe-sales');
        mohasebeSalesComponentsPage = new MohasebeSalesComponentsPage();
        expect(await mohasebeSalesComponentsPage.getTitle()).to.eq('insurancestartApp.mohasebeSales.home.title');
    });

    it('should load create MohasebeSales page', async () => {
        await mohasebeSalesComponentsPage.clickOnCreateButton();
        mohasebeSalesUpdatePage = new MohasebeSalesUpdatePage();
        expect(await mohasebeSalesUpdatePage.getPageTitle()).to.eq('insurancestartApp.mohasebeSales.home.createOrEditLabel');
        await mohasebeSalesUpdatePage.cancel();
    });

    it('should create and save MohasebeSales', async () => {
        const nbButtonsBeforeCreate = await mohasebeSalesComponentsPage.countDeleteButtons();

        await mohasebeSalesComponentsPage.clickOnCreateButton();
        await promise.all([
            mohasebeSalesUpdatePage.setNameSherkatInput('nameSherkat'),
            mohasebeSalesUpdatePage.setSaltakhfifInput('5'),
            mohasebeSalesUpdatePage.tipsSelectLastOption()
        ]);
        expect(await mohasebeSalesUpdatePage.getNameSherkatInput()).to.eq('nameSherkat');
        expect(await mohasebeSalesUpdatePage.getSaltakhfifInput()).to.eq('5');
        await mohasebeSalesUpdatePage.save();
        expect(await mohasebeSalesUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await mohasebeSalesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last MohasebeSales', async () => {
        const nbButtonsBeforeDelete = await mohasebeSalesComponentsPage.countDeleteButtons();
        await mohasebeSalesComponentsPage.clickOnLastDeleteButton();

        mohasebeSalesDeleteDialog = new MohasebeSalesDeleteDialog();
        expect(await mohasebeSalesDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.mohasebeSales.delete.question');
        await mohasebeSalesDeleteDialog.clickOnConfirmButton();

        expect(await mohasebeSalesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
