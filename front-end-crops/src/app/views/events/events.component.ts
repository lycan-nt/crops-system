import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { EventService } from '../service/event-service.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  displayedColumns: string[] = ['cdEvent', 'nmEvent', 'dtEvent', 'tpEvent', 'placeEvent'];
  eventData!: Event[];

  constructor(
    public auth: AuthService,
    private eventService: EventService
  ) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.eventService.findAll().subscribe((events: Event[]) => {
      this.eventData = events;
    })
  }

}

