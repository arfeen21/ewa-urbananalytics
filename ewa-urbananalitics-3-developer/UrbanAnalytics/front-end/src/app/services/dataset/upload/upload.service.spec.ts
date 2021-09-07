import { TestBed } from '@angular/core/testing';

import { UploadService } from './upload.service';
import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('UploadService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [HttpClient],
    imports: [
      HttpClientTestingModule,
      RouterTestingModule
    ]
  }));

  it('should be created', () => {
    const service: UploadService = TestBed.get(UploadService);
    expect(service).toBeTruthy();
  });
});
