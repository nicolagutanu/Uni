import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortGenreComponent } from './sort-genre.component';

describe('SortGenreComponent', () => {
  let component: SortGenreComponent;
  let fixture: ComponentFixture<SortGenreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortGenreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortGenreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
