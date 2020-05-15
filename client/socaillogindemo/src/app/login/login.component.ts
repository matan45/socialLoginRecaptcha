import { Component, OnInit } from '@angular/core';
import { AuthService, FacebookLoginProvider, GoogleLoginProvider } from 'angularx-social-login';
import { SocialloginServiceService } from '../sociallogin-service.service';
import { UserData } from './UserPayload';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  data: UserData;

  constructor(private authService: AuthService, private client: SocialloginServiceService) { }

  ngOnInit() { }

  public socialSignIn(socialProvider: string) {
    let socialPlatformProvider;
    if (socialProvider === 'facebook') {
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    } else if (socialProvider === 'google') {
      socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
    }
    this.authService.signIn(socialPlatformProvider).then(socialusers => {
      this.data = {
        name: socialusers.name,
        email: socialusers.email,
        photoUrl: socialusers.photoUrl
      };
      console.log(this.data);
      this.client.Savesresponse(this.data);
    });
  }
}
