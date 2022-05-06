import { TestBed } from '@angular/core/testing';

import { CommentaryService } from './commentary.service';

describe('CommentaryService', () => {
  let service: CommentaryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommentaryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
