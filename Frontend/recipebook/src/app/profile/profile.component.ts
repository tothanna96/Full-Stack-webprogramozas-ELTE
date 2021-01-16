import { Component, OnInit } from '@angular/core';

import { User } from '@core/interfaces/user.interface';
import { AuthService } from '@core/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {



  constructor() { }

  ngOnInit(): void {
  }

}
