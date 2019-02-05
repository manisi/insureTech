/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AnvaeKhodroComponentsPage, AnvaeKhodroDeleteDialog, AnvaeKhodroUpdatePage } from './anvae-khodro.page-object';

const expect = chai.expect;

describe('AnvaeKhodro e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let anvaeKhodroUpdatePage: AnvaeKhodroUpdatePage;
    let anvaeKhodroComponentsPage: AnvaeKhodroComponentsPage;
    let anvaeKhodroDeleteDialog: AnvaeKhodroDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load AnvaeKhodros', async () => {
        await navBarPage.goToEntity('anvae-khodro');
        anvaeKhodroComponentsPage = new AnvaeKhodroComponentsPage();
        await browser.wait(ec.visibilityOf(anvaeKhodroComponentsPage.title), 5000);
        expect(await anvaeKhodroComponentsPage.getTitle()).to.eq('insurancestartApp.anvaeKhodro.home.title');
    });

    it('should load create AnvaeKhodro page', async () => {
        await anvaeKhodroComponentsPage.clickOnCreateButton();
        anvaeKhodroUpdatePage = new AnvaeKhodroUpdatePage();
        expect(await anvaeKhodroUpdatePage.getPageTitle()).to.eq('insurancestartApp.anvaeKhodro.home.createOrEditLabel');
        await anvaeKhodroUpdatePage.cancel();
    });

    it('should create and save AnvaeKhodros', async () => {
        const nbButtonsBeforeCreate = await anvaeKhodroComponentsPage.countDeleteButtons();

        await anvaeKhodroComponentsPage.clickOnCreateButton();
        await promise.all([
            anvaeKhodroUpdatePage.setGrouhVasileInput('grouhVasile'),
            anvaeKhodroUpdatePage.setSystemVasileInput('systemVasile'),
            anvaeKhodroUpdatePage.setOnvanInput('onvan'),
            anvaeKhodroUpdatePage.setTonazhInput('tonazh'),
            anvaeKhodroUpdatePage.setTedadSarneshinInput('tedadSarneshin'),
            anvaeKhodroUpdatePage.setTedadSilandreInput('tedadSilandre'),
            anvaeKhodroUpdatePage.setDasteBandiInput('dasteBandi'),
            anvaeKhodroUpdatePage.setSavariTypeInput('savariType'),
            anvaeKhodroUpdatePage.grouhKhodroSelectLastOption()
        ]);
        expect(await anvaeKhodroUpdatePage.getGrouhVasileInput()).to.eq('grouhVasile');
        expect(await anvaeKhodroUpdatePage.getSystemVasileInput()).to.eq('systemVasile');
        expect(await anvaeKhodroUpdatePage.getOnvanInput()).to.eq('onvan');
        expect(await anvaeKhodroUpdatePage.getTonazhInput()).to.eq('tonazh');
        expect(await anvaeKhodroUpdatePage.getTedadSarneshinInput()).to.eq('tedadSarneshin');
        expect(await anvaeKhodroUpdatePage.getTedadSilandreInput()).to.eq('tedadSilandre');
        expect(await anvaeKhodroUpdatePage.getDasteBandiInput()).to.eq('dasteBandi');
        expect(await anvaeKhodroUpdatePage.getSavariTypeInput()).to.eq('savariType');
        const selectedFaal = anvaeKhodroUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await anvaeKhodroUpdatePage.getFaalInput().click();
            expect(await anvaeKhodroUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await anvaeKhodroUpdatePage.getFaalInput().click();
            expect(await anvaeKhodroUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await anvaeKhodroUpdatePage.save();
        expect(await anvaeKhodroUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await anvaeKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last AnvaeKhodro', async () => {
        const nbButtonsBeforeDelete = await anvaeKhodroComponentsPage.countDeleteButtons();
        await anvaeKhodroComponentsPage.clickOnLastDeleteButton();

        anvaeKhodroDeleteDialog = new AnvaeKhodroDeleteDialog();
        expect(await anvaeKhodroDeleteDialog.getDialogTitle()).to.eq('insurancestartApp.anvaeKhodro.delete.question');
        await anvaeKhodroDeleteDialog.clickOnConfirmButton();

        expect(await anvaeKhodroComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
