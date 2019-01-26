/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
    AdamKhesaratSarneshinComponentsPage,
    AdamKhesaratSarneshinDeleteDialog,
    AdamKhesaratSarneshinUpdatePage
} from './adam-khesarat-sarneshin.page-object';

const expect = chai.expect;

describe('AdamKhesaratSarneshin e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let adamKhesaratSarneshinUpdatePage: AdamKhesaratSarneshinUpdatePage;
    let adamKhesaratSarneshinComponentsPage: AdamKhesaratSarneshinComponentsPage;
    /*let adamKhesaratSarneshinDeleteDialog: AdamKhesaratSarneshinDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load AdamKhesaratSarneshins', async () => {
        await navBarPage.goToEntity('adam-khesarat-sarneshin');
        adamKhesaratSarneshinComponentsPage = new AdamKhesaratSarneshinComponentsPage();
        await browser.wait(ec.visibilityOf(adamKhesaratSarneshinComponentsPage.title), 5000);
        expect(await adamKhesaratSarneshinComponentsPage.getTitle()).to.eq('insurancestartApp.adamKhesaratSarneshin.home.title');
    });

    it('should load create AdamKhesaratSarneshin page', async () => {
        await adamKhesaratSarneshinComponentsPage.clickOnCreateButton();
        adamKhesaratSarneshinUpdatePage = new AdamKhesaratSarneshinUpdatePage();
        expect(await adamKhesaratSarneshinUpdatePage.getPageTitle()).to.eq(
            'insurancestartApp.adamKhesaratSarneshin.home.createOrEditLabel'
        );
        await adamKhesaratSarneshinUpdatePage.cancel();
    });

    /* it('should create and save AdamKhesaratSarneshins', async () => {
        const nbButtonsBeforeCreate = await adamKhesaratSarneshinComponentsPage.countDeleteButtons();

        await adamKhesaratSarneshinComponentsPage.clickOnCreateButton();
        await promise.all([
            adamKhesaratSarneshinUpdatePage.setSarneshinInput('5'),
            adamKhesaratSarneshinUpdatePage.noeSabegheSelectLastOption(),
            adamKhesaratSarneshinUpdatePage.sabegheSelectLastOption(),
        ]);
        expect(await adamKhesaratSarneshinUpdatePage.getSarneshinInput()).to.eq('5');
        const selectedFaal = adamKhesaratSarneshinUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await adamKhesaratSarneshinUpdatePage.getFaalInput().click();
            expect(await adamKhesaratSarneshinUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await adamKhesaratSarneshinUpdatePage.getFaalInput().click();
            expect(await adamKhesaratSarneshinUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await adamKhesaratSarneshinUpdatePage.save();
        expect(await adamKhesaratSarneshinUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await adamKhesaratSarneshinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last AdamKhesaratSarneshin', async () => {
        const nbButtonsBeforeDelete = await adamKhesaratSarneshinComponentsPage.countDeleteButtons();
        await adamKhesaratSarneshinComponentsPage.clickOnLastDeleteButton();

        adamKhesaratSarneshinDeleteDialog = new AdamKhesaratSarneshinDeleteDialog();
        expect(await adamKhesaratSarneshinDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.adamKhesaratSarneshin.delete.question');
        await adamKhesaratSarneshinDeleteDialog.clickOnConfirmButton();

        expect(await adamKhesaratSarneshinComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
