/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AshkhasComponentsPage, AshkhasDeleteDialog, AshkhasUpdatePage } from './ashkhas.page-object';

const expect = chai.expect;

describe('Ashkhas e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let ashkhasUpdatePage: AshkhasUpdatePage;
    let ashkhasComponentsPage: AshkhasComponentsPage;
    let ashkhasDeleteDialog: AshkhasDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Ashkhas', async () => {
        await navBarPage.goToEntity('ashkhas');
        ashkhasComponentsPage = new AshkhasComponentsPage();
        expect(await ashkhasComponentsPage.getTitle()).to.eq('insurancestartApp.ashkhas.home.title');
    });

    it('should load create Ashkhas page', async () => {
        await ashkhasComponentsPage.clickOnCreateButton();
        ashkhasUpdatePage = new AshkhasUpdatePage();
        expect(await ashkhasUpdatePage.getPageTitle()).to.eq('insurancestartApp.ashkhas.home.createOrEditLabel');
        await ashkhasUpdatePage.cancel();
    });

    it('should create and save Ashkhas', async () => {
        const nbButtonsBeforeCreate = await ashkhasComponentsPage.countDeleteButtons();

        await ashkhasComponentsPage.clickOnCreateButton();
        await promise.all([
            ashkhasUpdatePage.setFirstNameInput('firstName'),
            ashkhasUpdatePage.setLastNameInput('lastName'),
            ashkhasUpdatePage.setEmailInput('email'),
            ashkhasUpdatePage.setPhoneNumberInput('phoneNumber'),
            ashkhasUpdatePage.setHireDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
            ashkhasUpdatePage.noeShakhsSelectLastOption()
        ]);
        expect(await ashkhasUpdatePage.getFirstNameInput()).to.eq('firstName');
        expect(await ashkhasUpdatePage.getLastNameInput()).to.eq('lastName');
        expect(await ashkhasUpdatePage.getEmailInput()).to.eq('email');
        expect(await ashkhasUpdatePage.getPhoneNumberInput()).to.eq('phoneNumber');
        expect(await ashkhasUpdatePage.getHireDateInput()).to.contain('2001-01-01T02:30');
        await ashkhasUpdatePage.save();
        expect(await ashkhasUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await ashkhasComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Ashkhas', async () => {
        const nbButtonsBeforeDelete = await ashkhasComponentsPage.countDeleteButtons();
        await ashkhasComponentsPage.clickOnLastDeleteButton();

        ashkhasDeleteDialog = new AshkhasDeleteDialog();
        expect(await ashkhasDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.ashkhas.delete.question');
        await ashkhasDeleteDialog.clickOnConfirmButton();

        expect(await ashkhasComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
