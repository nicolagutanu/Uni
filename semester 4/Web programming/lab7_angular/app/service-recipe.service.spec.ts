import { TestBed } from '@angular/core/testing';

import { ServiceRecipeService } from './service-recipe.service';

describe('ServiceRecipeService', () => {
  let service: ServiceRecipeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceRecipeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
