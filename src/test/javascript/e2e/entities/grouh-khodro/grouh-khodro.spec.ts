/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GrouhKhodroComponentsPage, GrouhKhodroDeleteDialog, GrouhKhodroUpdatePage } from './grouh-khodro.page-object';

const expect = chai.expect;

describe('GrouhKhodro e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let grouhKhodroUpdatePage: GrouhKhodroUpdatePage;
    let grouhKhodroComponentsPage: GrouhKhodroComponentsPage;
    let grouhKhodroDeleteDialog: GrouhKhodroDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load GrouhKhodros', async () => {
        await navBarPage.goToEntity('grouh-khodro');
        grouhKhodroComponentsPage = new GrouhKhodroComponentsPage();
        await browser.wait(ec.visibilityOf(grouhKhodroComponentsPage.title), 5000);
        expect(await grouhKhodroComponentsPage.getTitle()).to.eq('insurancestartApp.grouhKhodro.home.title');
    });

    it('should load create GrouhKhodro page', async () => {
        await grouhKhodroComponentsPage.clickOnCreateButton();
        grouhKhodroUpdatePage = new GrouhKhodroUpdatePage();
        expect(await grouhKhodroUpdatePage.getPageTitle()).to.eq('insurancestartApp.grouhKhodro.home.createOrEditLabel');
        await grouhKhodroUpdatePage.cancel();
    });

    it('should create and save GrouhKhodros', async () => {
        const nbButtonsBeforeCreate = await grouhKhodroComponentsPage.countDeleteButtons();

        await grouhKhodroComponentsPage.clickOnCreateButton();
        await promise.all([grouhKhodroUpdatePage.setNameInput('name'), grouhKhodroUpdatePage.setCodeInput('5')]);
        expect(await grouhKhodroUpdatePage.getNameInput()).to.eq('name');
        const selectedFaal = grouhKhodroUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await grouhKhodroUpdatePage.getFaalInput().click();
            expect(await grouhKhodroUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await grouhKhodroUpdatePage.getFaalInput().click();
            expect(await grouhKhodroUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        expect(await grouhKhodroUpdatePage.getCodeInput()).to.eq('5');
        await grouhKhodroUpdatePage.save();
        expect(await grouhKhodroUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await grouhKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last GrouhKhodro', async () => {
        const nbButtonsBeforeDelete = await grouhKhodroComponentsPage.countDeleteButtons();
        await grouhKhodroComponentsPage.clickOnLastDeleteButton();

        grouhKhodroDeleteDialog = new GrouhKhodroDeleteDialog();
        expect(await grouhKhodroDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.grouhKhodro.delete.question');
        await grouhKhodroDeleteDialog.clickOnConfirmButton();

        expect(await grouhKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
