/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SherkatBimeComponentsPage, SherkatBimeDeleteDialog, SherkatBimeUpdatePage } from './sherkat-bime.page-object';

const expect = chai.expect;

describe('SherkatBime e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let sherkatBimeUpdatePage: SherkatBimeUpdatePage;
    let sherkatBimeComponentsPage: SherkatBimeComponentsPage;
    let sherkatBimeDeleteDialog: SherkatBimeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load SherkatBimes', async () => {
        await navBarPage.goToEntity('sherkat-bime');
        sherkatBimeComponentsPage = new SherkatBimeComponentsPage();
        expect(await sherkatBimeComponentsPage.getTitle()).to.eq('insurancestartApp.sherkatBime.home.title');
    });

    it('should load create SherkatBime page', async () => {
        await sherkatBimeComponentsPage.clickOnCreateButton();
        sherkatBimeUpdatePage = new SherkatBimeUpdatePage();
        expect(await sherkatBimeUpdatePage.getPageTitle()).to.eq('insurancestartApp.sherkatBime.home.createOrEditLabel');
        await sherkatBimeUpdatePage.cancel();
    });

    it('should create and save SherkatBimes', async () => {
        const nbButtonsBeforeCreate = await sherkatBimeComponentsPage.countDeleteButtons();

        await sherkatBimeComponentsPage.clickOnCreateButton();
        await promise.all([sherkatBimeUpdatePage.setNameInput('name')]);
        expect(await sherkatBimeUpdatePage.getNameInput()).to.eq('name');
        const selectedFaal = sherkatBimeUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await sherkatBimeUpdatePage.getFaalInput().click();
            expect(await sherkatBimeUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await sherkatBimeUpdatePage.getFaalInput().click();
            expect(await sherkatBimeUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await sherkatBimeUpdatePage.save();
        expect(await sherkatBimeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await sherkatBimeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last SherkatBime', async () => {
        const nbButtonsBeforeDelete = await sherkatBimeComponentsPage.countDeleteButtons();
        await sherkatBimeComponentsPage.clickOnLastDeleteButton();

        sherkatBimeDeleteDialog = new SherkatBimeDeleteDialog();
        expect(await sherkatBimeDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.sherkatBime.delete.question');
        await sherkatBimeDeleteDialog.clickOnConfirmButton();

        expect(await sherkatBimeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
