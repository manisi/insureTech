/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { KhesaratSalesComponentsPage, KhesaratSalesDeleteDialog, KhesaratSalesUpdatePage } from './khesarat-sales.page-object';

const expect = chai.expect;

describe('KhesaratSales e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let khesaratSalesUpdatePage: KhesaratSalesUpdatePage;
    let khesaratSalesComponentsPage: KhesaratSalesComponentsPage;
    /*let khesaratSalesDeleteDialog: KhesaratSalesDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load KhesaratSales', async () => {
        await navBarPage.goToEntity('khesarat-sales');
        khesaratSalesComponentsPage = new KhesaratSalesComponentsPage();
        await browser.wait(ec.visibilityOf(khesaratSalesComponentsPage.title), 5000);
        expect(await khesaratSalesComponentsPage.getTitle()).to.eq('insurancestartApp.khesaratSales.home.title');
    });

    it('should load create KhesaratSales page', async () => {
        await khesaratSalesComponentsPage.clickOnCreateButton();
        khesaratSalesUpdatePage = new KhesaratSalesUpdatePage();
        expect(await khesaratSalesUpdatePage.getPageTitle()).to.eq('insurancestartApp.khesaratSales.home.createOrEditLabel');
        await khesaratSalesUpdatePage.cancel();
    });

    /* it('should create and save KhesaratSales', async () => {
        const nbButtonsBeforeCreate = await khesaratSalesComponentsPage.countDeleteButtons();

        await khesaratSalesComponentsPage.clickOnCreateButton();
        await promise.all([
            khesaratSalesUpdatePage.noeSelectLastOption(),
            khesaratSalesUpdatePage.setNerkhKhesaratInput('5'),
            khesaratSalesUpdatePage.sabegheSelectLastOption(),
            khesaratSalesUpdatePage.noeSabegheSelectLastOption(),
        ]);
        expect(await khesaratSalesUpdatePage.getNerkhKhesaratInput()).to.eq('5');
        const selectedFaal = khesaratSalesUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await khesaratSalesUpdatePage.getFaalInput().click();
            expect(await khesaratSalesUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await khesaratSalesUpdatePage.getFaalInput().click();
            expect(await khesaratSalesUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await khesaratSalesUpdatePage.save();
        expect(await khesaratSalesUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await khesaratSalesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last KhesaratSales', async () => {
        const nbButtonsBeforeDelete = await khesaratSalesComponentsPage.countDeleteButtons();
        await khesaratSalesComponentsPage.clickOnLastDeleteButton();

        khesaratSalesDeleteDialog = new KhesaratSalesDeleteDialog();
        expect(await khesaratSalesDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.khesaratSales.delete.question');
        await khesaratSalesDeleteDialog.clickOnConfirmButton();

        expect(await khesaratSalesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
