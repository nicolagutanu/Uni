import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortCinemaComponent } from './sort-cinema.component';

describe('SortCinemaComponent', () => {
  let component: SortCinemaComponent;
  let fixture: ComponentFixture<SortCinemaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortCinemaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortCinemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
