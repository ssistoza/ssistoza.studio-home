import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public titles = [ 'Welcome', 'Aloha', 'Sawatdee', 'Alola' ];
  public title: String = this.titles[0];
  public transition: boolean = true;
  private selectedTitle: number = 0;

  constructor() { }

  /**
   * Changes the current title of the Landing Page.
   *  - This should only be changed once the title has transitioned out.
   * @return     {<type>}  { description_of_the_return_value }
   */
  changeTitle() {
    if (this.selectedTitle >= this.titles.length-1) {
      this.selectedTitle = -1; // Preperation to index to zero.
    }
    
    this.selectedTitle++;
    this.title = this.titles[this.selectedTitle];
  }

  ngOnInit() {
    const timer = Observable.timer(2000, 1500);
    timer.subscribe(t => {
      if ( this.transition ) {
        // ready to transition to next title screen
        this.transition = false;
      } else {
        this.changeTitle();
        // Scale In Transition Frist.
        this.transition = true;
      }
    });
  } // ngOnInit()
}

