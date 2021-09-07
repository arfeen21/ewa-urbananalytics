import { TestBed } from '@angular/core/testing';

import { RequestService } from './request.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

describe('RequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule,
      RouterTestingModule,
      FormsModule
    ]
  }));

  // Jerry
  // Component should be created
  it('should be created', () => {
    const service: RequestService = TestBed.get(RequestService);
    expect(service).toBeTruthy();
  });

  // Jerry
  // postRequest method should be called
  it('should call postRequest method', () => {
    const service: RequestService = TestBed.get(RequestService);

    spyOn(service, "postRequest");
    service.postRequest("/dataset/all", {}, (dataset) => {
    });

    expect(service.postRequest).toHaveBeenCalled();
  })

  // Jerry
  // getRequest method should be called
  it('should call getRequest method', () => {
    const service: RequestService = TestBed.get(RequestService);

    spyOn(service, "getRequest");
    service.getRequest("/dataset/all", (dataset) => {
    });

    expect(service.getRequest).toHaveBeenCalled();
  })
});
