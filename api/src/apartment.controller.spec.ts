import { Test, TestingModule } from '@nestjs/testing';
import { ApartmentController } from './apartment.controller';
import { ApartmentService } from './apartment.service';

describe('ApartmentController', () => {
  let appController: ApartmentController;

  beforeEach(async () => {
    const app: TestingModule = await Test.createTestingModule({
      controllers: [ApartmentController],
      providers: [ApartmentService],
    }).compile();

    appController = app.get<ApartmentController>(ApartmentController);
  });

  describe('root', () => {
    it('should return list of apartment', () => {
      expect(appController.getAllC()).toEqual(expect.any(Array));
    });
  });
});
