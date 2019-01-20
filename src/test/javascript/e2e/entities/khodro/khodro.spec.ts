/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { KhodroComponentsPage, KhodroDeleteDialog, KhodroUpdatePage } from './khodro.page-object';

const expect = chai.expect;

describe('Khodro e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let khodroUpdatePage: KhodroUpdatePage;
    let khodroComponentsPage: KhodroComponentsPage;
    let khodroDeleteDialog: KhodroDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Khodros', async () => {
        await navBarPage.goToEntity('khodro');
        khodroComponentsPage = new KhodroComponentsPage();
        expect(await khodroComponentsPage.getTitle()).to.eq('insurancestartApp.khodro.home.title');
    });

    it('should load create Khodro page', async () => {
        await khodroComponentsPage.clickOnCreateButton();
        khodroUpdatePage = new KhodroUpdatePage();
        expect(await khodroUpdatePage.getPageTitle()).to.eq('insurancestartApp.khodro.home.createOrEditLabel');
        await khodroUpdatePage.cancel();
    });

    it('should create and save Khodros', async () => {
        const nbButtonsBeforeCreate = await khodroComponentsPage.countDeleteButtons();

        await khodroComponentsPage.clickOnCreateButton();
        await promise.all([
            khodroUpdatePage.setNameInput('name'),
            khodroUpdatePage.setModelInput('model'),
            khodroUpdatePage.noeSelectLastOption()
        ]);
        expect(await khodroUpdatePage.getNameInput()).to.eq('name');
        expect(await khodroUpdatePage.getModelInput()).to.eq('model');
        const selectedFaal = khodroUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await khodroUpdatePage.getFaalInput().click();
            expect(await khodroUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await khodroUpdatePage.getFaalInput().click();
            expect(await khodroUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await khodroUpdatePage.save();
        expect(await khodroUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await khodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Khodro', async () => {
        const nbButtonsBeforeDelete = await khodroComponentsPage.countDeleteButtons();
        await khodroComponentsPage.clickOnLastDeleteButton();

        khodroDeleteDialog = new KhodroDeleteDialog();
        expect(await khodroDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.khodro.delete.question');
        await khodroDeleteDialog.clickOnConfirmButton();

        expect(await khodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
