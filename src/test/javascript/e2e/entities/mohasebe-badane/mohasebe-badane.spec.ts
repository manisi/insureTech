/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MohasebeBadaneComponentsPage, MohasebeBadaneDeleteDialog, MohasebeBadaneUpdatePage } from './mohasebe-badane.page-object';

const expect = chai.expect;

describe('MohasebeBadane e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let mohasebeBadaneUpdatePage: MohasebeBadaneUpdatePage;
    let mohasebeBadaneComponentsPage: MohasebeBadaneComponentsPage;
    let mohasebeBadaneDeleteDialog: MohasebeBadaneDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load MohasebeBadanes', async () => {
        await navBarPage.goToEntity('mohasebe-badane');
        mohasebeBadaneComponentsPage = new MohasebeBadaneComponentsPage();
        expect(await mohasebeBadaneComponentsPage.getTitle()).to.eq('insurancestartApp.mohasebeBadane.home.title');
    });

    it('should load create MohasebeBadane page', async () => {
        await mohasebeBadaneComponentsPage.clickOnCreateButton();
        mohasebeBadaneUpdatePage = new MohasebeBadaneUpdatePage();
        expect(await mohasebeBadaneUpdatePage.getPageTitle()).to.eq('insurancestartApp.mohasebeBadane.home.createOrEditLabel');
        await mohasebeBadaneUpdatePage.cancel();
    });

    it('should create and save MohasebeBadanes', async () => {
        const nbButtonsBeforeCreate = await mohasebeBadaneComponentsPage.countDeleteButtons();

        await mohasebeBadaneComponentsPage.clickOnCreateButton();
        await promise.all([
            mohasebeBadaneUpdatePage.setNameSherkatInput('nameSherkat'),
            mohasebeBadaneUpdatePage.setSaltakhfifInput('5'),
            mohasebeBadaneUpdatePage.tipsSelectLastOption()
        ]);
        expect(await mohasebeBadaneUpdatePage.getNameSherkatInput()).to.eq('nameSherkat');
        expect(await mohasebeBadaneUpdatePage.getSaltakhfifInput()).to.eq('5');
        await mohasebeBadaneUpdatePage.save();
        expect(await mohasebeBadaneUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await mohasebeBadaneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last MohasebeBadane', async () => {
        const nbButtonsBeforeDelete = await mohasebeBadaneComponentsPage.countDeleteButtons();
        await mohasebeBadaneComponentsPage.clickOnLastDeleteButton();

        mohasebeBadaneDeleteDialog = new MohasebeBadaneDeleteDialog();
        expect(await mohasebeBadaneDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.mohasebeBadane.delete.question');
        await mohasebeBadaneDeleteDialog.clickOnConfirmButton();

        expect(await mohasebeBadaneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
