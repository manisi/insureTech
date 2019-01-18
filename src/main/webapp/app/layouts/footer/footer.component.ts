import { Component } from '@angular/core';
import { faTwitter } from '@fortawesome/free-brands-svg-icons';
import { faGithub } from '@fortawesome/free-brands-svg-icons';
import { faAngular } from '@fortawesome/free-brands-svg-icons';
import { faJava } from '@fortawesome/free-brands-svg-icons';
import { faGooglePlay } from '@fortawesome/free-brands-svg-icons';
import { faAppStore } from '@fortawesome/free-brands-svg-icons';
@Component({
    selector: 'jhi-footer',
    templateUrl: './footer.component.html',
    styleUrls: ['footer.scss']
})
export class FooterComponent {
    faTwitter = faTwitter;
    faGithub = faGithub;
    faAngular = faAngular;
    faJava = faJava;
    faAppStore = faAppStore;
    faGooglePlay = faGooglePlay;
}
