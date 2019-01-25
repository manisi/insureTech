/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NerkhComponentsPage, NerkhDeleteDialog, NerkhUpdatePage } from './nerkh.page-object';

const expect = chai.expect;

describe('Nerkh e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let nerkhUpdatePage: NerkhUpdatePage;
    let nerkhComponentsPage: NerkhComponentsPage;
    let nerkhDeleteDialog: NerkhDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Nerkhs', async () => {
        await navBarPage.goToEntity('nerkh');
        nerkhComponentsPage = new NerkhComponentsPage();
        expect(await nerkhComponentsPage.getTitle()).to.eq('insurancestartApp.nerkh.home.title');
    });

    it('should load create Nerkh page', async () => {
        await nerkhComponentsPage.clickOnCreateButton();
        nerkhUpdatePage = new NerkhUpdatePage();
        expect(await nerkhUpdatePage.getPageTitle()).to.eq('insurancestartApp.nerkh.home.createOrEditLabel');
        await nerkhUpdatePage.cancel();
    });

    it('should create and save Nerkhs', async () => {
        const nbButtonsBeforeCreate = await nerkhComponentsPage.countDeleteButtons();

        await nerkhComponentsPage.clickOnCreateButton();
        await promise.all([
            nerkhUpdatePage.setNameInput('name'),
            nerkhUpdatePage.setMablaghInput('5')
            // nerkhUpdatePage.sherkatBimeSelectLastOption(),
        ]);
        expect(await nerkhUpdatePage.getNameInput()).to.eq('name');
        const selectedFaal = nerkhUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await nerkhUpdatePage.getFaalInput().click();
            expect(await nerkhUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await nerkhUpdatePage.getFaalInput().click();
            expect(await nerkhUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        expect(await nerkhUpdatePage.getMablaghInput()).to.eq('5');
        await nerkhUpdatePage.save();
        expect(await nerkhUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await nerkhComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Nerkh', async () => {
        const nbButtonsBeforeDelete = await nerkhComponentsPage.countDeleteButtons();
        await nerkhComponentsPage.clickOnLastDeleteButton();

        nerkhDeleteDialog = new NerkhDeleteDialog();
        expect(await nerkhDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.nerkh.delete.question');
        await nerkhDeleteDialog.clickOnConfirmButton();

        expect(await nerkhComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
