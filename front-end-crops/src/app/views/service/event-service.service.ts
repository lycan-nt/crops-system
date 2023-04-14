import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventDTO } from '../model/event';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private apiUrl = "http://localhost:8080/api/v1/events/"

  constructor(
    private httpClient: HttpClient
  ) { }

  findAll() : Observable<EventDTO[]> {
    return this.httpClient.get<EventDTO[]>(this.apiUrl);
  }

}
