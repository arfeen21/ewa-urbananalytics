import { TestBed, async } from '@angular/core/testing';

import { DatasetService } from './dataset.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { DatasetModelComponent } from 'src/app/models/dataset/dataset.model';

describe('DatasetService', () => {
  const dataset: DatasetModelComponent = new DatasetModelComponent("Nieuwe dataset");

  beforeEach(() => TestBed.configureTestingModule({
    imports: [
      HttpClientTestingModule,
      RouterTestingModule
    ],
  }));

  // Jerry
  // Service should be created
  it('should be created', () => {
    const service: DatasetService = TestBed.get(DatasetService);
    expect(service).toBeTruthy();
  });

  // Jerry
  // Create a dataset on the backend
  it('should create a dataset', () => {
    const service: DatasetService = TestBed.get(DatasetService);

    spyOn(service, "createDataset");
    service.createDataset(() => { }, dataset);

    expect(service.createDataset).toHaveBeenCalled();
  })

  // Jerry
  // Get all datasets from back-end
  it('should return all users datasets', async(() => {
    const service: DatasetService = TestBed.get(DatasetService);
    spyOn(service, "getMyItems");

    service.getMyItems(() => { });

    expect(service.getMyItems).toHaveBeenCalled();
  }))
});
