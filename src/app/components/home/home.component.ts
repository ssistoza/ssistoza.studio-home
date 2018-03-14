import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public title = [ 'Welcome', 'Aloha', 'Sawatdee', 'Alola' ];

  constructor() { }

  showTitle() { return this.title[0]; }

  ngOnInit() {
  }
}
