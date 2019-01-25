/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { JarimeDirkardComponentsPage, JarimeDirkardDeleteDialog, JarimeDirkardUpdatePage } from './jarime-dirkard.page-object';

const expect = chai.expect;

describe('JarimeDirkard e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let jarimeDirkardUpdatePage: JarimeDirkardUpdatePage;
    let jarimeDirkardComponentsPage: JarimeDirkardComponentsPage;
    /*let jarimeDirkardDeleteDialog: JarimeDirkardDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load JarimeDirkards', async () => {
        await navBarPage.goToEntity('jarime-dirkard');
        jarimeDirkardComponentsPage = new JarimeDirkardComponentsPage();
        expect(await jarimeDirkardComponentsPage.getTitle()).to.eq('insurancestartApp.jarimeDirkard.home.title');
    });

    it('should load create JarimeDirkard page', async () => {
        await jarimeDirkardComponentsPage.clickOnCreateButton();
        jarimeDirkardUpdatePage = new JarimeDirkardUpdatePage();
        expect(await jarimeDirkardUpdatePage.getPageTitle()).to.eq('insurancestartApp.jarimeDirkard.home.createOrEditLabel');
        await jarimeDirkardUpdatePage.cancel();
    });

    /* it('should create and save JarimeDirkards', async () => {
        const nbButtonsBeforeCreate = await jarimeDirkardComponentsPage.countDeleteButtons();

        await jarimeDirkardComponentsPage.clickOnCreateButton();
        await promise.all([
            jarimeDirkardUpdatePage.setRoozaneInput('5'),
            jarimeDirkardUpdatePage.grouhKhodroSelectLastOption(),
        ]);
        expect(await jarimeDirkardUpdatePage.getRoozaneInput()).to.eq('5');
        const selectedFaal = jarimeDirkardUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await jarimeDirkardUpdatePage.getFaalInput().click();
            expect(await jarimeDirkardUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await jarimeDirkardUpdatePage.getFaalInput().click();
            expect(await jarimeDirkardUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await jarimeDirkardUpdatePage.save();
        expect(await jarimeDirkardUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await jarimeDirkardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last JarimeDirkard', async () => {
        const nbButtonsBeforeDelete = await jarimeDirkardComponentsPage.countDeleteButtons();
        await jarimeDirkardComponentsPage.clickOnLastDeleteButton();

        jarimeDirkardDeleteDialog = new JarimeDirkardDeleteDialog();
        expect(await jarimeDirkardDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.jarimeDirkard.delete.question');
        await jarimeDirkardDeleteDialog.clickOnConfirmButton();

        expect(await jarimeDirkardComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
