import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterCinemaComponent } from './filter-cinema.component';

describe('FilterCinemaComponent', () => {
  let component: FilterCinemaComponent;
  let fixture: ComponentFixture<FilterCinemaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterCinemaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterCinemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
