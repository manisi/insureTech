/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SabegheKhesaratComponentsPage, SabegheKhesaratDeleteDialog, SabegheKhesaratUpdatePage } from './sabeghe-khesarat.page-object';

const expect = chai.expect;

describe('SabegheKhesarat e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let sabegheKhesaratUpdatePage: SabegheKhesaratUpdatePage;
    let sabegheKhesaratComponentsPage: SabegheKhesaratComponentsPage;
    let sabegheKhesaratDeleteDialog: SabegheKhesaratDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SabegheKhesarats', async () => {
        await navBarPage.goToEntity('sabeghe-khesarat');
        sabegheKhesaratComponentsPage = new SabegheKhesaratComponentsPage();
        await browser.wait(ec.visibilityOf(sabegheKhesaratComponentsPage.title), 5000);
        expect(await sabegheKhesaratComponentsPage.getTitle()).to.eq('insurancestartApp.sabegheKhesarat.home.title');
    });

    it('should load create SabegheKhesarat page', async () => {
        await sabegheKhesaratComponentsPage.clickOnCreateButton();
        sabegheKhesaratUpdatePage = new SabegheKhesaratUpdatePage();
        expect(await sabegheKhesaratUpdatePage.getPageTitle()).to.eq('insurancestartApp.sabegheKhesarat.home.createOrEditLabel');
        await sabegheKhesaratUpdatePage.cancel();
    });

    it('should create and save SabegheKhesarats', async () => {
        const nbButtonsBeforeCreate = await sabegheKhesaratComponentsPage.countDeleteButtons();

        await sabegheKhesaratComponentsPage.clickOnCreateButton();
        await promise.all([sabegheKhesaratUpdatePage.setNameInput('name'), sabegheKhesaratUpdatePage.setSharhInput('sharh')]);
        expect(await sabegheKhesaratUpdatePage.getNameInput()).to.eq('name');
        expect(await sabegheKhesaratUpdatePage.getSharhInput()).to.eq('sharh');
        const selectedFaal = sabegheKhesaratUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await sabegheKhesaratUpdatePage.getFaalInput().click();
            expect(await sabegheKhesaratUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await sabegheKhesaratUpdatePage.getFaalInput().click();
            expect(await sabegheKhesaratUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await sabegheKhesaratUpdatePage.save();
        expect(await sabegheKhesaratUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await sabegheKhesaratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last SabegheKhesarat', async () => {
        const nbButtonsBeforeDelete = await sabegheKhesaratComponentsPage.countDeleteButtons();
        await sabegheKhesaratComponentsPage.clickOnLastDeleteButton();

        sabegheKhesaratDeleteDialog = new SabegheKhesaratDeleteDialog();
        expect(await sabegheKhesaratDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.sabegheKhesarat.delete.question');
        await sabegheKhesaratDeleteDialog.clickOnConfirmButton();

        expect(await sabegheKhesaratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
