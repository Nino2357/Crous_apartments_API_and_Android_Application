import {
  Controller,
  Get,
  Post,
  Body,
  Param,
  Query,
  Delete, Put, Patch,
} from '@nestjs/common';
import { ApartmentService } from './apartment.service';
import { Apartment } from './Apartment';
import {Application} from "express";
import {ApartmentLessInfo} from "./ApartmentLessInfo";

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
  putMark(@Param('id') id: string, @Body() favorites: boolean): Apartment|string{
    console.log(id);
    console.log(favorites["favorites"]);
    if(favorites["favorites"]=="true") {
      return this.ApartmentService.addMark(Number(id));
    }
    else {
      return "nothing append"
    }
  }

  @Delete(':id')
  deleteMark(@Param('id') id: string): Apartment {
    console.log("remove mark");
    console.log(id);
    return this.ApartmentService.deleteMark(Number(id));
  }
}
