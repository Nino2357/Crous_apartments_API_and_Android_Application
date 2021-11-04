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
  getApartmentIDorFavorites(@Param('id') id: string): Apartment[]|Apartment{
    if(id==="Favorites") {
      return this.ApartmentService.getFavorites();
    }
    else {
      return this.ApartmentService.getApartmentID(Number(id));
    }
  }

  @Put(':id')
  putMark(@Param() params){
    console.log("Put mark");
    return this.ApartmentService.addMark(params.id);
  }

  @Delete(':id')
  deleteMark(@Param() params) {
    console.log("remove mark");
    return this.ApartmentService.deleteMark(params.id);
  }
}
