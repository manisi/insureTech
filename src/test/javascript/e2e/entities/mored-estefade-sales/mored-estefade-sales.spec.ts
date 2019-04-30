/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    MoredEstefadeSalesComponentsPage,
    MoredEstefadeSalesDeleteDialog,
    MoredEstefadeSalesUpdatePage
} from './mored-estefade-sales.page-object';

const expect = chai.expect;

describe('MoredEstefadeSales e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let moredEstefadeSalesUpdatePage: MoredEstefadeSalesUpdatePage;
    let moredEstefadeSalesComponentsPage: MoredEstefadeSalesComponentsPage;
    /*let moredEstefadeSalesDeleteDialog: MoredEstefadeSalesDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load MoredEstefadeSales', async () => {
        await navBarPage.goToEntity('mored-estefade-sales');
        moredEstefadeSalesComponentsPage = new MoredEstefadeSalesComponentsPage();
        await browser.wait(ec.visibilityOf(moredEstefadeSalesComponentsPage.title), 5000);
        expect(await moredEstefadeSalesComponentsPage.getTitle()).to.eq('insurancestartApp.moredEstefadeSales.home.title');
    });

    it('should load create MoredEstefadeSales page', async () => {
        await moredEstefadeSalesComponentsPage.clickOnCreateButton();
        moredEstefadeSalesUpdatePage = new MoredEstefadeSalesUpdatePage();
        expect(await moredEstefadeSalesUpdatePage.getPageTitle()).to.eq('insurancestartApp.moredEstefadeSales.home.createOrEditLabel');
        await moredEstefadeSalesUpdatePage.cancel();
    });

    /* it('should create and save MoredEstefadeSales', async () => {
        const nbButtonsBeforeCreate = await moredEstefadeSalesComponentsPage.countDeleteButtons();

        await moredEstefadeSalesComponentsPage.clickOnCreateButton();
        await promise.all([
            moredEstefadeSalesUpdatePage.setDarsadEzafeNerkhInput('5'),
            moredEstefadeSalesUpdatePage.setAzTarikhInput('2000-12-31'),
            moredEstefadeSalesUpdatePage.setTaTarikhInput('2000-12-31'),
            moredEstefadeSalesUpdatePage.grouhKhodroSelectLastOption(),
            moredEstefadeSalesUpdatePage.sherkatBimeSelectLastOption(),
            moredEstefadeSalesUpdatePage.onvanKhodroSelectLastOption(),
        ]);
        expect(await moredEstefadeSalesUpdatePage.getDarsadEzafeNerkhInput()).to.eq('5');
        expect(await moredEstefadeSalesUpdatePage.getAzTarikhInput()).to.eq('2000-12-31');
        expect(await moredEstefadeSalesUpdatePage.getTaTarikhInput()).to.eq('2000-12-31');
        const selectedFaal = moredEstefadeSalesUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await moredEstefadeSalesUpdatePage.getFaalInput().click();
            expect(await moredEstefadeSalesUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await moredEstefadeSalesUpdatePage.getFaalInput().click();
            expect(await moredEstefadeSalesUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await moredEstefadeSalesUpdatePage.save();
        expect(await moredEstefadeSalesUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await moredEstefadeSalesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last MoredEstefadeSales', async () => {
        const nbButtonsBeforeDelete = await moredEstefadeSalesComponentsPage.countDeleteButtons();
        await moredEstefadeSalesComponentsPage.clickOnLastDeleteButton();

        moredEstefadeSalesDeleteDialog = new MoredEstefadeSalesDeleteDialog();
        expect(await moredEstefadeSalesDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.moredEstefadeSales.delete.question');
        await moredEstefadeSalesDeleteDialog.clickOnConfirmButton();

        expect(await moredEstefadeSalesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
