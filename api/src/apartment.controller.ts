import {
  Controller,
  Get,
  Post,
  Body,
  Param,
  Query,
  Delete,
} from '@nestjs/common';
import { ApartmentService } from './apartment.service';
import { Apartment } from './Apartment';

@Controller('/apartments')
export class ApartmentController {
  constructor(private readonly ApartmentService: ApartmentService) {}

  @Get()
  getAllC(): Apartment[] {
      return this.ApartmentService.getAllApartments();
    }
  //
  @Post()
  addApartmentC(@Body() apartment: Apartment): Apartment {
    this.ApartmentService.addApartment(apartment);
    return apartment;
  }

  @Get(':id')
  getApartmentC(@Param() params): Apartment | undefined {
    return this.ApartmentService.getApartment(params.id);
  }

  // @Delete(':id')
  // deleteBookC(@Param() params) {
  //   this.ApartmentService.deleteBook(params.id);
  // }
}
