import { browser, ExpectedConditions as ec /* , promise */ } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    EstelaamSalesNerkhComponentsPage,
    /* EstelaamSalesNerkhDeleteDialog,
     */ EstelaamSalesNerkhUpdatePage
} from './estelaam-sales-nerkh.page-object';

const expect = chai.expect;

describe('EstelaamSalesNerkh e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let estelaamSalesNerkhComponentsPage: EstelaamSalesNerkhComponentsPage;
    let estelaamSalesNerkhUpdatePage: EstelaamSalesNerkhUpdatePage;
    /* let estelaamSalesNerkhDeleteDialog: EstelaamSalesNerkhDeleteDialog; */

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load EstelaamSalesNerkhs', async () => {
        await navBarPage.goToEntity('estelaam-sales-nerkh');
        estelaamSalesNerkhComponentsPage = new EstelaamSalesNerkhComponentsPage();
        await browser.wait(ec.visibilityOf(estelaamSalesNerkhComponentsPage.title), 5000);
        expect(await estelaamSalesNerkhComponentsPage.getTitle()).to.eq('insurancestartApp.estelaamSalesNerkh.home.title');
    });

    it('should load create EstelaamSalesNerkh page', async () => {
        await estelaamSalesNerkhComponentsPage.clickOnCreateButton();
        estelaamSalesNerkhUpdatePage = new EstelaamSalesNerkhUpdatePage();
        expect(await estelaamSalesNerkhUpdatePage.getPageTitle()).to.eq('insurancestartApp.estelaamSalesNerkh.home.createOrEditLabel');
        await estelaamSalesNerkhUpdatePage.cancel();
    });

    /*  it('should create and save EstelaamSalesNerkhs', async () => {
        const nbButtonsBeforeCreate = await estelaamSalesNerkhComponentsPage.countDeleteButtons();

        await estelaamSalesNerkhComponentsPage.clickOnCreateButton();
        await promise.all([
            estelaamSalesNerkhUpdatePage.anvaeKhodroSelectLastOption(),
            estelaamSalesNerkhUpdatePage.saalSakhtSelectLastOption(),
            estelaamSalesNerkhUpdatePage.onvanKhodroSelectLastOption(),
            estelaamSalesNerkhUpdatePage.adamKhesaratSelectLastOption(),
            estelaamSalesNerkhUpdatePage.adamKhesaratSarneshinSelectLastOption(),
            estelaamSalesNerkhUpdatePage.khesaratSrneshinSelectLastOption(),
            estelaamSalesNerkhUpdatePage.khesaratSalesSelectLastOption(),
        ]);
        await estelaamSalesNerkhUpdatePage.save();
        expect(await estelaamSalesNerkhUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

        expect(await estelaamSalesNerkhComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
    }); */

    /*  it('should delete last EstelaamSalesNerkh', async () => {
        const nbButtonsBeforeDelete = await estelaamSalesNerkhComponentsPage.countDeleteButtons();
        await estelaamSalesNerkhComponentsPage.clickOnLastDeleteButton();

        estelaamSalesNerkhDeleteDialog = new EstelaamSalesNerkhDeleteDialog();
        expect(await estelaamSalesNerkhDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.estelaamSalesNerkh.delete.question');
        await estelaamSalesNerkhDeleteDialog.clickOnConfirmButton();

        expect(await estelaamSalesNerkhComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    }); */

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
