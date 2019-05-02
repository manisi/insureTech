/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { KohnegiBadaneComponentsPage, KohnegiBadaneDeleteDialog, KohnegiBadaneUpdatePage } from './kohnegi-badane.page-object';

const expect = chai.expect;

describe('KohnegiBadane e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let kohnegiBadaneUpdatePage: KohnegiBadaneUpdatePage;
    let kohnegiBadaneComponentsPage: KohnegiBadaneComponentsPage;
    /*let kohnegiBadaneDeleteDialog: KohnegiBadaneDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load KohnegiBadanes', async () => {
        await navBarPage.goToEntity('kohnegi-badane');
        kohnegiBadaneComponentsPage = new KohnegiBadaneComponentsPage();
        await browser.wait(ec.visibilityOf(kohnegiBadaneComponentsPage.title), 5000);
        expect(await kohnegiBadaneComponentsPage.getTitle()).to.eq('insurancestartApp.kohnegiBadane.home.title');
    });

    it('should load create KohnegiBadane page', async () => {
        await kohnegiBadaneComponentsPage.clickOnCreateButton();
        kohnegiBadaneUpdatePage = new KohnegiBadaneUpdatePage();
        expect(await kohnegiBadaneUpdatePage.getPageTitle()).to.eq('insurancestartApp.kohnegiBadane.home.createOrEditLabel');
        await kohnegiBadaneUpdatePage.cancel();
    });

    /* it('should create and save KohnegiBadanes', async () => {
        const nbButtonsBeforeCreate = await kohnegiBadaneComponentsPage.countDeleteButtons();

        await kohnegiBadaneComponentsPage.clickOnCreateButton();
        await promise.all([
            kohnegiBadaneUpdatePage.setDarsadHarSalInput('5'),
            kohnegiBadaneUpdatePage.setMaxDarsadInput('5'),
            kohnegiBadaneUpdatePage.setSharhInput('sharh'),
            kohnegiBadaneUpdatePage.grouhKhodroSelectLastOption(),
            kohnegiBadaneUpdatePage.sherkatBimeSelectLastOption(),
        ]);
        expect(await kohnegiBadaneUpdatePage.getDarsadHarSalInput()).to.eq('5');
        expect(await kohnegiBadaneUpdatePage.getMaxDarsadInput()).to.eq('5');
        expect(await kohnegiBadaneUpdatePage.getSharhInput()).to.eq('sharh');
        const selectedFaal = kohnegiBadaneUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await kohnegiBadaneUpdatePage.getFaalInput().click();
            expect(await kohnegiBadaneUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await kohnegiBadaneUpdatePage.getFaalInput().click();
            expect(await kohnegiBadaneUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await kohnegiBadaneUpdatePage.save();
        expect(await kohnegiBadaneUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await kohnegiBadaneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last KohnegiBadane', async () => {
        const nbButtonsBeforeDelete = await kohnegiBadaneComponentsPage.countDeleteButtons();
        await kohnegiBadaneComponentsPage.clickOnLastDeleteButton();

        kohnegiBadaneDeleteDialog = new KohnegiBadaneDeleteDialog();
        expect(await kohnegiBadaneDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.kohnegiBadane.delete.question');
        await kohnegiBadaneDeleteDialog.clickOnConfirmButton();

        expect(await kohnegiBadaneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
