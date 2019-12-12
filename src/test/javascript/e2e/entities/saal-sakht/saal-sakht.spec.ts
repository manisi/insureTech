/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SaalSakhtComponentsPage, SaalSakhtDeleteDialog, SaalSakhtUpdatePage } from './saal-sakht.page-object';

const expect = chai.expect;

describe('SaalSakht e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let saalSakhtUpdatePage: SaalSakhtUpdatePage;
    let saalSakhtComponentsPage: SaalSakhtComponentsPage;
    let saalSakhtDeleteDialog: SaalSakhtDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SaalSakhts', async () => {
        await navBarPage.goToEntity('saal-sakht');
        saalSakhtComponentsPage = new SaalSakhtComponentsPage();
        await browser.wait(ec.visibilityOf(saalSakhtComponentsPage.title), 5000);
        expect(await saalSakhtComponentsPage.getTitle()).to.eq('insurancestartApp.saalSakht.home.title');
    });

    it('should load create SaalSakht page', async () => {
        await saalSakhtComponentsPage.clickOnCreateButton();
        saalSakhtUpdatePage = new SaalSakhtUpdatePage();
        expect(await saalSakhtUpdatePage.getPageTitle()).to.eq('insurancestartApp.saalSakht.home.createOrEditLabel');
        await saalSakhtUpdatePage.cancel();
    });

    it('should create and save SaalSakhts', async () => {
        const nbButtonsBeforeCreate = await saalSakhtComponentsPage.countDeleteButtons();

        await saalSakhtComponentsPage.clickOnCreateButton();
        await promise.all([saalSakhtUpdatePage.setSaalShamsiInput('saalShamsi'), saalSakhtUpdatePage.setSaalMiladiInput('saalMiladi')]);
        expect(await saalSakhtUpdatePage.getSaalShamsiInput()).to.eq('saalShamsi');
        expect(await saalSakhtUpdatePage.getSaalMiladiInput()).to.eq('saalMiladi');
        await saalSakhtUpdatePage.save();
        expect(await saalSakhtUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await saalSakhtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last SaalSakht', async () => {
        const nbButtonsBeforeDelete = await saalSakhtComponentsPage.countDeleteButtons();
        await saalSakhtComponentsPage.clickOnLastDeleteButton();

        saalSakhtDeleteDialog = new SaalSakhtDeleteDialog();
        expect(await saalSakhtDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.saalSakht.delete.question');
        await saalSakhtDeleteDialog.clickOnConfirmButton();

        expect(await saalSakhtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
