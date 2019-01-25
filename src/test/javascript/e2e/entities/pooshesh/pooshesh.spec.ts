/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PoosheshComponentsPage, PoosheshDeleteDialog, PoosheshUpdatePage } from './pooshesh.page-object';

const expect = chai.expect;

describe('Pooshesh e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let poosheshUpdatePage: PoosheshUpdatePage;
    let poosheshComponentsPage: PoosheshComponentsPage;
    let poosheshDeleteDialog: PoosheshDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Poosheshes', async () => {
        await navBarPage.goToEntity('pooshesh');
        poosheshComponentsPage = new PoosheshComponentsPage();
        expect(await poosheshComponentsPage.getTitle()).to.eq('insurancestartApp.pooshesh.home.title');
    });

    it('should load create Pooshesh page', async () => {
        await poosheshComponentsPage.clickOnCreateButton();
        poosheshUpdatePage = new PoosheshUpdatePage();
        expect(await poosheshUpdatePage.getPageTitle()).to.eq('insurancestartApp.pooshesh.home.createOrEditLabel');
        await poosheshUpdatePage.cancel();
    });

    it('should create and save Poosheshes', async () => {
        const nbButtonsBeforeCreate = await poosheshComponentsPage.countDeleteButtons();

        await poosheshComponentsPage.clickOnCreateButton();
        await promise.all([
            poosheshUpdatePage.setNameInput('name'),
            poosheshUpdatePage.setAztarikhInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            poosheshUpdatePage.nerkhSelectLastOption()
        ]);
        expect(await poosheshUpdatePage.getNameInput()).to.eq('name');
        const selectedFaal = poosheshUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await poosheshUpdatePage.getFaalInput().click();
            expect(await poosheshUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await poosheshUpdatePage.getFaalInput().click();
            expect(await poosheshUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        expect(await poosheshUpdatePage.getAztarikhInput()).to.contain('2001-01-01T02:30');
        await poosheshUpdatePage.save();
        expect(await poosheshUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await poosheshComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Pooshesh', async () => {
        const nbButtonsBeforeDelete = await poosheshComponentsPage.countDeleteButtons();
        await poosheshComponentsPage.clickOnLastDeleteButton();

        poosheshDeleteDialog = new PoosheshDeleteDialog();
        expect(await poosheshDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.pooshesh.delete.question');
        await poosheshDeleteDialog.clickOnConfirmButton();

        expect(await poosheshComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
