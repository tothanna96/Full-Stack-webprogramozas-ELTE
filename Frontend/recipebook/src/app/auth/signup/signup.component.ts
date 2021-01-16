import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

import { AuthService } from '@core/services/auth.service';
import { NotificationService } from '@core/services/notification.service';

import { MatchValidation } from '@core/validators/match.validator';

import { User } from '@core/interfaces/user.interface';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  public signupForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    protected auth: AuthService,
		private ns: NotificationService
  ) {
    this.signupForm = this.formBuilder.group({
			username: [null, [Validators.minLength(4), Validators.required]],
			email: [null, [Validators.email, Validators.required]],
			password: [null, [Validators.required, Validators.minLength(6)]],
			passwordConfirm: [null, Validators.required]
		},
		{
			validator: MatchValidation.MatchPassword
		});
  }

  signup(form: FormGroup): void {
		if (form.valid) {
      delete form.value.passwordConfirm;
      this.auth.register(<User>form.value);
      console.log(form.value);
      this.signupForm.reset();
		}
		else {
			this.ns.show('HIBA! Az adatok nem megfelelőek!');
		}
	}

}
