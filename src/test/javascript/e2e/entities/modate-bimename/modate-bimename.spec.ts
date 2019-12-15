/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ModateBimenameComponentsPage, ModateBimenameDeleteDialog, ModateBimenameUpdatePage } from './modate-bimename.page-object';

const expect = chai.expect;

describe('ModateBimename e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let modateBimenameUpdatePage: ModateBimenameUpdatePage;
    let modateBimenameComponentsPage: ModateBimenameComponentsPage;
    let modateBimenameDeleteDialog: ModateBimenameDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load ModateBimenames', async () => {
        await navBarPage.goToEntity('modate-bimename');
        modateBimenameComponentsPage = new ModateBimenameComponentsPage();
        await browser.wait(ec.visibilityOf(modateBimenameComponentsPage.title), 5000);
        expect(await modateBimenameComponentsPage.getTitle()).to.eq('insurancestartApp.modateBimename.home.title');
    });

    it('should load create ModateBimename page', async () => {
        await modateBimenameComponentsPage.clickOnCreateButton();
        modateBimenameUpdatePage = new ModateBimenameUpdatePage();
        expect(await modateBimenameUpdatePage.getPageTitle()).to.eq('insurancestartApp.modateBimename.home.createOrEditLabel');
        await modateBimenameUpdatePage.cancel();
    });

    it('should create and save ModateBimenames', async () => {
        const nbButtonsBeforeCreate = await modateBimenameComponentsPage.countDeleteButtons();

        await modateBimenameComponentsPage.clickOnCreateButton();
        await promise.all([modateBimenameUpdatePage.setNameInput('name'), modateBimenameUpdatePage.setSharhInput('sharh')]);
        expect(await modateBimenameUpdatePage.getNameInput()).to.eq('name');
        expect(await modateBimenameUpdatePage.getSharhInput()).to.eq('sharh');
        const selectedFaal = modateBimenameUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await modateBimenameUpdatePage.getFaalInput().click();
            expect(await modateBimenameUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await modateBimenameUpdatePage.getFaalInput().click();
            expect(await modateBimenameUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await modateBimenameUpdatePage.save();
        expect(await modateBimenameUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await modateBimenameComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last ModateBimename', async () => {
        const nbButtonsBeforeDelete = await modateBimenameComponentsPage.countDeleteButtons();
        await modateBimenameComponentsPage.clickOnLastDeleteButton();

        modateBimenameDeleteDialog = new ModateBimenameDeleteDialog();
        expect(await modateBimenameDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.modateBimename.delete.question');
        await modateBimenameDeleteDialog.clickOnConfirmButton();

        expect(await modateBimenameComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
