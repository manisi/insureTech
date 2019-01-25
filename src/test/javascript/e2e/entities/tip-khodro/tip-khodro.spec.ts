/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TipKhodroComponentsPage, TipKhodroDeleteDialog, TipKhodroUpdatePage } from './tip-khodro.page-object';

const expect = chai.expect;

describe('TipKhodro e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let tipKhodroUpdatePage: TipKhodroUpdatePage;
    let tipKhodroComponentsPage: TipKhodroComponentsPage;
    let tipKhodroDeleteDialog: TipKhodroDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load TipKhodros', async () => {
        await navBarPage.goToEntity('tip-khodro');
        tipKhodroComponentsPage = new TipKhodroComponentsPage();
        expect(await tipKhodroComponentsPage.getTitle()).to.eq('insurancestartApp.tipKhodro.home.title');
    });

    it('should load create TipKhodro page', async () => {
        await tipKhodroComponentsPage.clickOnCreateButton();
        tipKhodroUpdatePage = new TipKhodroUpdatePage();
        expect(await tipKhodroUpdatePage.getPageTitle()).to.eq('insurancestartApp.tipKhodro.home.createOrEditLabel');
        await tipKhodroUpdatePage.cancel();
    });

    it('should create and save TipKhodros', async () => {
        const nbButtonsBeforeCreate = await tipKhodroComponentsPage.countDeleteButtons();

        await tipKhodroComponentsPage.clickOnCreateButton();
        await promise.all([
            tipKhodroUpdatePage.setNameInput('name'),
            tipKhodroUpdatePage.setModelInput('model'),
            tipKhodroUpdatePage.noeSelectLastOption(),
            tipKhodroUpdatePage.khodroSelectLastOption()
        ]);
        expect(await tipKhodroUpdatePage.getNameInput()).to.eq('name');
        expect(await tipKhodroUpdatePage.getModelInput()).to.eq('model');
        const selectedFaal = tipKhodroUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await tipKhodroUpdatePage.getFaalInput().click();
            expect(await tipKhodroUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await tipKhodroUpdatePage.getFaalInput().click();
            expect(await tipKhodroUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await tipKhodroUpdatePage.save();
        expect(await tipKhodroUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await tipKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last TipKhodro', async () => {
        const nbButtonsBeforeDelete = await tipKhodroComponentsPage.countDeleteButtons();
        await tipKhodroComponentsPage.clickOnLastDeleteButton();

        tipKhodroDeleteDialog = new TipKhodroDeleteDialog();
        expect(await tipKhodroDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.tipKhodro.delete.question');
        await tipKhodroDeleteDialog.clickOnConfirmButton();

        expect(await tipKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
