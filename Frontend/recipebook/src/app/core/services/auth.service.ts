import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { NotificationService } from '@core/services/notification.service';

import { User } from '@core/interfaces/user.interface';

import { baseUrl } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLogin$ = new BehaviorSubject<boolean>(this.hasToken());

  users = new BehaviorSubject<User[]>([]);

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': ''
    })
  };

  constructor(
    private http: HttpClient,
    private router: Router,
    private ns : NotificationService
  ) { }

  isLoggedIn(): Observable<boolean> {
    return this.isLogin$.asObservable();
  }

  //REGISTER
  register(user: User): void {
    this.http.post<User>(`${baseUrl}/users`, JSON.stringify(user), this.httpOptions).subscribe(
      data => {
        this.ns.show('Sikeres regisztráció!');
      },
      error => {
        this.ns.show('HIBA! Hibás regisztráció!');
        console.error(error);
      }
    );
  }

  //LOGIN
  login(user: User): void {
    this.http.post<User>(`${baseUrl}/users/login`, user, this.httpOptions).subscribe(
      data => {
        localStorage.setItem('token', data['token']);
        this.isLogin$.next(true);
        this.ns.show('Sikeres bejelentkezés!');
        this.router.navigate(['/recipes/open']);
      },
      error => {
        this.ns.show('HIBA! Hibás bejelentkezés!');
        console.error(error);
      }
    );
  }

  getUsers(): void{
    this.http.get<User[]>(`${baseUrl}/users`)
            .subscribe(r => {
                this.users.next(r);
            });
  }

  //LOGOUT
  logout(): void {
    localStorage.removeItem('token');
    this.isLogin$.next(false);
    this.ns.show('Sikeres kijelentkezés!');
    this.router.navigate(['/']);
  }

  protected hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

}
