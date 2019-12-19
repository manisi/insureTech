/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    KhesaratSalesMaliComponentsPage,
    KhesaratSalesMaliDeleteDialog,
    KhesaratSalesMaliUpdatePage
} from './khesarat-sales-mali.page-object';

const expect = chai.expect;

describe('KhesaratSalesMali e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let khesaratSalesMaliUpdatePage: KhesaratSalesMaliUpdatePage;
    let khesaratSalesMaliComponentsPage: KhesaratSalesMaliComponentsPage;
    /*let khesaratSalesMaliDeleteDialog: KhesaratSalesMaliDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load KhesaratSalesMalis', async () => {
        await navBarPage.goToEntity('khesarat-sales-mali');
        khesaratSalesMaliComponentsPage = new KhesaratSalesMaliComponentsPage();
        await browser.wait(ec.visibilityOf(khesaratSalesMaliComponentsPage.title), 5000);
        expect(await khesaratSalesMaliComponentsPage.getTitle()).to.eq('insurancestartApp.khesaratSalesMali.home.title');
    });

    it('should load create KhesaratSalesMali page', async () => {
        await khesaratSalesMaliComponentsPage.clickOnCreateButton();
        khesaratSalesMaliUpdatePage = new KhesaratSalesMaliUpdatePage();
        expect(await khesaratSalesMaliUpdatePage.getPageTitle()).to.eq('insurancestartApp.khesaratSalesMali.home.createOrEditLabel');
        await khesaratSalesMaliUpdatePage.cancel();
    });

    /* it('should create and save KhesaratSalesMalis', async () => {
        const nbButtonsBeforeCreate = await khesaratSalesMaliComponentsPage.countDeleteButtons();

        await khesaratSalesMaliComponentsPage.clickOnCreateButton();
        await promise.all([
            khesaratSalesMaliUpdatePage.noeSelectLastOption(),
            khesaratSalesMaliUpdatePage.setNerkhKhesaratMaliInput('5'),
            khesaratSalesMaliUpdatePage.sabegheSelectLastOption(),
            khesaratSalesMaliUpdatePage.noeSabegheSelectLastOption(),
        ]);
        expect(await khesaratSalesMaliUpdatePage.getNerkhKhesaratMaliInput()).to.eq('5');
        const selectedFaal = khesaratSalesMaliUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await khesaratSalesMaliUpdatePage.getFaalInput().click();
            expect(await khesaratSalesMaliUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await khesaratSalesMaliUpdatePage.getFaalInput().click();
            expect(await khesaratSalesMaliUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await khesaratSalesMaliUpdatePage.save();
        expect(await khesaratSalesMaliUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await khesaratSalesMaliComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last KhesaratSalesMali', async () => {
        const nbButtonsBeforeDelete = await khesaratSalesMaliComponentsPage.countDeleteButtons();
        await khesaratSalesMaliComponentsPage.clickOnLastDeleteButton();

        khesaratSalesMaliDeleteDialog = new KhesaratSalesMaliDeleteDialog();
        expect(await khesaratSalesMaliDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.khesaratSalesMali.delete.question');
        await khesaratSalesMaliDeleteDialog.clickOnConfirmButton();

        expect(await khesaratSalesMaliComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
