/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SabegheComponentsPage, SabegheDeleteDialog, SabegheUpdatePage } from './sabeghe.page-object';

const expect = chai.expect;

describe('Sabeghe e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let sabegheUpdatePage: SabegheUpdatePage;
    let sabegheComponentsPage: SabegheComponentsPage;
    let sabegheDeleteDialog: SabegheDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Sabeghes', async () => {
        await navBarPage.goToEntity('sabeghe');
        sabegheComponentsPage = new SabegheComponentsPage();
        expect(await sabegheComponentsPage.getTitle()).to.eq('insurancestartApp.sabeghe.home.title');
    });

    it('should load create Sabeghe page', async () => {
        await sabegheComponentsPage.clickOnCreateButton();
        sabegheUpdatePage = new SabegheUpdatePage();
        expect(await sabegheUpdatePage.getPageTitle()).to.eq('insurancestartApp.sabeghe.home.createOrEditLabel');
        await sabegheUpdatePage.cancel();
    });

    it('should create and save Sabeghes', async () => {
        const nbButtonsBeforeCreate = await sabegheComponentsPage.countDeleteButtons();

        await sabegheComponentsPage.clickOnCreateButton();
        await promise.all([sabegheUpdatePage.setNameInput('name'), sabegheUpdatePage.setSharhInput('sharh')]);
        expect(await sabegheUpdatePage.getNameInput()).to.eq('name');
        expect(await sabegheUpdatePage.getSharhInput()).to.eq('sharh');
        const selectedFaal = sabegheUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await sabegheUpdatePage.getFaalInput().click();
            expect(await sabegheUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await sabegheUpdatePage.getFaalInput().click();
            expect(await sabegheUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await sabegheUpdatePage.save();
        expect(await sabegheUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await sabegheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Sabeghe', async () => {
        const nbButtonsBeforeDelete = await sabegheComponentsPage.countDeleteButtons();
        await sabegheComponentsPage.clickOnLastDeleteButton();

        sabegheDeleteDialog = new SabegheDeleteDialog();
        expect(await sabegheDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.sabeghe.delete.question');
        await sabegheDeleteDialog.clickOnConfirmButton();

        expect(await sabegheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
