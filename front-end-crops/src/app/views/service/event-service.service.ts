import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiUrl = "http://localhost:8080/api/v1/events/"

  constructor(
    private httpClient: HttpClient
  ) { }

  findAll() : Observable<Event[]> {
    console.log("FINDALL")
    return this.httpClient.get<Event[]>(this.apiUrl);
  }

}
