import {HttpModule, Module} from '@nestjs/common';
import { ApartmentController } from './apartment.controller';
import { ApartmentService } from './apartment.service';

@Module({
  imports: [HttpModule],
  controllers: [ApartmentController],
  providers: [ApartmentService],
})
export class ApartmentModule {}
