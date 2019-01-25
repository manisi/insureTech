/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { KohnegiComponentsPage, KohnegiDeleteDialog, KohnegiUpdatePage } from './kohnegi.page-object';

const expect = chai.expect;

describe('Kohnegi e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let kohnegiUpdatePage: KohnegiUpdatePage;
    let kohnegiComponentsPage: KohnegiComponentsPage;
    let kohnegiDeleteDialog: KohnegiDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Kohnegis', async () => {
        await navBarPage.goToEntity('kohnegi');
        kohnegiComponentsPage = new KohnegiComponentsPage();
        await browser.wait(ec.visibilityOf(kohnegiComponentsPage.title), 5000);
        expect(await kohnegiComponentsPage.getTitle()).to.eq('insurancestartApp.kohnegi.home.title');
    });

    it('should load create Kohnegi page', async () => {
        await kohnegiComponentsPage.clickOnCreateButton();
        kohnegiUpdatePage = new KohnegiUpdatePage();
        expect(await kohnegiUpdatePage.getPageTitle()).to.eq('insurancestartApp.kohnegi.home.createOrEditLabel');
        await kohnegiUpdatePage.cancel();
    });

    it('should create and save Kohnegis', async () => {
        const nbButtonsBeforeCreate = await kohnegiComponentsPage.countDeleteButtons();

        await kohnegiComponentsPage.clickOnCreateButton();
        await promise.all([
            kohnegiUpdatePage.setDarsadHarSalInput('5'),
            kohnegiUpdatePage.setMaxDarsadInput('5'),
            kohnegiUpdatePage.setSharhInput('sharh')
        ]);
        expect(await kohnegiUpdatePage.getDarsadHarSalInput()).to.eq('5');
        expect(await kohnegiUpdatePage.getMaxDarsadInput()).to.eq('5');
        expect(await kohnegiUpdatePage.getSharhInput()).to.eq('sharh');
        const selectedFaal = kohnegiUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await kohnegiUpdatePage.getFaalInput().click();
            expect(await kohnegiUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await kohnegiUpdatePage.getFaalInput().click();
            expect(await kohnegiUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await kohnegiUpdatePage.save();
        expect(await kohnegiUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await kohnegiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Kohnegi', async () => {
        const nbButtonsBeforeDelete = await kohnegiComponentsPage.countDeleteButtons();
        await kohnegiComponentsPage.clickOnLastDeleteButton();

        kohnegiDeleteDialog = new KohnegiDeleteDialog();
        expect(await kohnegiDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.kohnegi.delete.question');
        await kohnegiDeleteDialog.clickOnConfirmButton();

        expect(await kohnegiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
