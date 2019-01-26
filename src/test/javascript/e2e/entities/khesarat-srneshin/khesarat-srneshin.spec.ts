/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { KhesaratSrneshinComponentsPage, KhesaratSrneshinDeleteDialog, KhesaratSrneshinUpdatePage } from './khesarat-srneshin.page-object';

const expect = chai.expect;

describe('KhesaratSrneshin e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let khesaratSrneshinUpdatePage: KhesaratSrneshinUpdatePage;
    let khesaratSrneshinComponentsPage: KhesaratSrneshinComponentsPage;
    /*let khesaratSrneshinDeleteDialog: KhesaratSrneshinDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load KhesaratSrneshins', async () => {
        await navBarPage.goToEntity('khesarat-srneshin');
        khesaratSrneshinComponentsPage = new KhesaratSrneshinComponentsPage();
        await browser.wait(ec.visibilityOf(khesaratSrneshinComponentsPage.title), 5000);
        expect(await khesaratSrneshinComponentsPage.getTitle()).to.eq('insurancestartApp.khesaratSrneshin.home.title');
    });

    it('should load create KhesaratSrneshin page', async () => {
        await khesaratSrneshinComponentsPage.clickOnCreateButton();
        khesaratSrneshinUpdatePage = new KhesaratSrneshinUpdatePage();
        expect(await khesaratSrneshinUpdatePage.getPageTitle()).to.eq('insurancestartApp.khesaratSrneshin.home.createOrEditLabel');
        await khesaratSrneshinUpdatePage.cancel();
    });

    /* it('should create and save KhesaratSrneshins', async () => {
        const nbButtonsBeforeCreate = await khesaratSrneshinComponentsPage.countDeleteButtons();

        await khesaratSrneshinComponentsPage.clickOnCreateButton();
        await promise.all([
            khesaratSrneshinUpdatePage.setNerkhKhesaratInput('5'),
            khesaratSrneshinUpdatePage.noeSabegheSelectLastOption(),
            khesaratSrneshinUpdatePage.sabegheSelectLastOption(),
        ]);
        expect(await khesaratSrneshinUpdatePage.getNerkhKhesaratInput()).to.eq('5');
        const selectedFaal = khesaratSrneshinUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await khesaratSrneshinUpdatePage.getFaalInput().click();
            expect(await khesaratSrneshinUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await khesaratSrneshinUpdatePage.getFaalInput().click();
            expect(await khesaratSrneshinUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await khesaratSrneshinUpdatePage.save();
        expect(await khesaratSrneshinUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await khesaratSrneshinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last KhesaratSrneshin', async () => {
        const nbButtonsBeforeDelete = await khesaratSrneshinComponentsPage.countDeleteButtons();
        await khesaratSrneshinComponentsPage.clickOnLastDeleteButton();

        khesaratSrneshinDeleteDialog = new KhesaratSrneshinDeleteDialog();
        expect(await khesaratSrneshinDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.khesaratSrneshin.delete.question');
        await khesaratSrneshinDeleteDialog.clickOnConfirmButton();

        expect(await khesaratSrneshinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
