import { Component, OnInit } from '@angular/core';
import { ReCaptchaV3Service } from 'ng-recaptcha';
import { SocialloginServiceService } from '../sociallogin-service.service';

@Component({
  selector: 'app-recaptcha',
  templateUrl: './recaptcha.component.html',
  styleUrls: ['./recaptcha.component.css']
})
export class RecaptchaComponent implements OnInit {

  constructor(private recaptchaV3Service: ReCaptchaV3Service, private client: SocialloginServiceService) { }

  ngOnInit(): void {
  }
  public executeImportantAction(): void {
    this.recaptchaV3Service.execute('recaptcha')
      .subscribe(token => {
        console.log(token);
        this.client.Savesresponsetoken(token).subscribe(success => console.log(success.score+","+success.success));
      });
  }
}
