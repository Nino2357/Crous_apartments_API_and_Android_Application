import {
  Controller,
  Get,
  Post,
  Body,
  Param,
  Query,
  Delete, Put,
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

  @Post()
  addApartmentC(@Body() apartment: Apartment): Apartment {
    this.ApartmentService.addApartment(apartment);
    return apartment;
  }

  @Get(':id')
  getApartmentID(@Param('id') id: number): Apartment{
    return this.ApartmentService.getApartmentID(id);
  }

  @Put(':id')
  putMark(@Param() params){
    this.ApartmentService.addMark(params.id);
  }

  @Delete(':id')
  deleteMark(@Param() params) {
    this.ApartmentService.deleteMark(params.id);
  }
}
