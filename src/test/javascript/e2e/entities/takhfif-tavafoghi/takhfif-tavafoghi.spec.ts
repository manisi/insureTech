/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TakhfifTavafoghiComponentsPage, TakhfifTavafoghiDeleteDialog, TakhfifTavafoghiUpdatePage } from './takhfif-tavafoghi.page-object';

const expect = chai.expect;

describe('TakhfifTavafoghi e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let takhfifTavafoghiUpdatePage: TakhfifTavafoghiUpdatePage;
    let takhfifTavafoghiComponentsPage: TakhfifTavafoghiComponentsPage;
    /*let takhfifTavafoghiDeleteDialog: TakhfifTavafoghiDeleteDialog;*/

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load TakhfifTavafoghis', async () => {
        await navBarPage.goToEntity('takhfif-tavafoghi');
        takhfifTavafoghiComponentsPage = new TakhfifTavafoghiComponentsPage();
        await browser.wait(ec.visibilityOf(takhfifTavafoghiComponentsPage.title), 5000);
        expect(await takhfifTavafoghiComponentsPage.getTitle()).to.eq('insurancestartApp.takhfifTavafoghi.home.title');
    });

    it('should load create TakhfifTavafoghi page', async () => {
        await takhfifTavafoghiComponentsPage.clickOnCreateButton();
        takhfifTavafoghiUpdatePage = new TakhfifTavafoghiUpdatePage();
        expect(await takhfifTavafoghiUpdatePage.getPageTitle()).to.eq('insurancestartApp.takhfifTavafoghi.home.createOrEditLabel');
        await takhfifTavafoghiUpdatePage.cancel();
    });

    /* it('should create and save TakhfifTavafoghis', async () => {
        const nbButtonsBeforeCreate = await takhfifTavafoghiComponentsPage.countDeleteButtons();

        await takhfifTavafoghiComponentsPage.clickOnCreateButton();
        await promise.all([
            takhfifTavafoghiUpdatePage.setNameInput('name'),
            takhfifTavafoghiUpdatePage.setDarsadTakhfifInput('5'),
            takhfifTavafoghiUpdatePage.setAzTarikhInput('2000-12-31'),
            takhfifTavafoghiUpdatePage.sherkatBimeSelectLastOption(),
        ]);
        expect(await takhfifTavafoghiUpdatePage.getNameInput()).to.eq('name');
        expect(await takhfifTavafoghiUpdatePage.getDarsadTakhfifInput()).to.eq('5');
        expect(await takhfifTavafoghiUpdatePage.getAzTarikhInput()).to.eq('2000-12-31');
        const selectedFaal = takhfifTavafoghiUpdatePage.getFaalInput();
        if (await selectedFaal.isSelected()) {
            await takhfifTavafoghiUpdatePage.getFaalInput().click();
            expect(await takhfifTavafoghiUpdatePage.getFaalInput().isSelected()).to.be.false;
        } else {
            await takhfifTavafoghiUpdatePage.getFaalInput().click();
            expect(await takhfifTavafoghiUpdatePage.getFaalInput().isSelected()).to.be.true;
        }
        await takhfifTavafoghiUpdatePage.save();
        expect(await takhfifTavafoghiUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await takhfifTavafoghiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });*/

    /* it('should delete last TakhfifTavafoghi', async () => {
        const nbButtonsBeforeDelete = await takhfifTavafoghiComponentsPage.countDeleteButtons();
        await takhfifTavafoghiComponentsPage.clickOnLastDeleteButton();

        takhfifTavafoghiDeleteDialog = new TakhfifTavafoghiDeleteDialog();
        expect(await takhfifTavafoghiDeleteDialog.getDialogTitle())
            .to.eq('insurancestartApp.takhfifTavafoghi.delete.question');
        await takhfifTavafoghiDeleteDialog.clickOnConfirmButton();

        expect(await takhfifTavafoghiComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });*/

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
