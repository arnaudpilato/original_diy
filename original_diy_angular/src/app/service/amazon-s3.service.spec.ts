import { TestBed } from '@angular/core/testing';

import { AmazonS3Service } from './amazon-s3.service';

describe('AmazonS3Service', () => {
  let service: AmazonS3Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AmazonS3Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
