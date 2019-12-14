/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VaziatBimeComponentsPage, VaziatBimeDeleteDialog, VaziatBimeUpdatePage } from './vaziat-bime.page-object';

const expect = chai.expect;

describe('VaziatBime e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let vaziatBimeUpdatePage: VaziatBimeUpdatePage;
    let vaziatBimeComponentsPage: VaziatBimeComponentsPage;
    let vaziatBimeDeleteDialog: VaziatBimeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load VaziatBimes', async () => {
        await navBarPage.goToEntity('vaziat-bime');
        vaziatBimeComponentsPage = new VaziatBimeComponentsPage();
        await browser.wait(ec.visibilityOf(vaziatBimeComponentsPage.title), 5000);
        expect(await vaziatBimeComponentsPage.getTitle()).to.eq('insurancestartApp.vaziatBime.home.title');
    });

    it('should load create VaziatBime page', async () => {
        await vaziatBimeComponentsPage.clickOnCreateButton();
        vaziatBimeUpdatePage = new VaziatBimeUpdatePage();
        expect(await vaziatBimeUpdatePage.getPageTitle()).to.eq('insurancestartApp.vaziatBime.home.createOrEditLabel');
        await vaziatBimeUpdatePage.cancel();
    });

    it('should create and save VaziatBimes', async () => {
        const nbButtonsBeforeCreate = await vaziatBimeComponentsPage.countDeleteButtons();

        await vaziatBimeComponentsPage.clickOnCreateButton();
        await promise.all([vaziatBimeUpdatePage.setTitleInput('title')]);
        expect(await vaziatBimeUpdatePage.getTitleInput()).to.eq('title');
        const selectedFaal = vaziatBimeUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await vaziatBimeUpdatePage.getFaalInput().click();
            expect(await vaziatBimeUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await vaziatBimeUpdatePage.getFaalInput().click();
            expect(await vaziatBimeUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await vaziatBimeUpdatePage.save();
        expect(await vaziatBimeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await vaziatBimeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last VaziatBime', async () => {
        const nbButtonsBeforeDelete = await vaziatBimeComponentsPage.countDeleteButtons();
        await vaziatBimeComponentsPage.clickOnLastDeleteButton();

        vaziatBimeDeleteDialog = new VaziatBimeDeleteDialog();
        expect(await vaziatBimeDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.vaziatBime.delete.question');
        await vaziatBimeDeleteDialog.clickOnConfirmButton();

        expect(await vaziatBimeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
