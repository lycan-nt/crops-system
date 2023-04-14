import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from '../auth/auth.service';
import { EventDTO } from '../model/event';
import { EventService } from '../service/event-service.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  displayedColumns: string[] = ['cdEvent', 'nmEvent', 'dtEvent', 'tpEvent', 'placeEvent', 'action'];
  eventData!: any[];
  dataSource!: MatTableDataSource<any>;
  searchKey!: string;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort; 
 
  constructor(
    public auth: AuthService,
    private eventService: EventService
  ) { }

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.eventService.findAll().subscribe({
      next:events => {
        this.eventData = events;
        this.dataSource = new MatTableDataSource(this.eventData);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
    },
    error: err => console.log("Error: " + err)
    })
  }

  filterChange() {
    this.dataSource.filter = this.searchKey.trim().toLocaleLowerCase();
  }

  editEvent(row: any) {
    console.log('Edit', row);
  }

  deleteEvent(row: any) {
    console.log('Delete: ' , row);
  }

  onSearchClear() {
    this.searchKey = "";
    this.findAll();
  }

}

