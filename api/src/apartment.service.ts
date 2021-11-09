import {HttpService, Injectable, OnModuleInit} from '@nestjs/common';
import { Apartment } from './Apartment';
import { ApartmentImported } from './ApartmentImported';

import { buffer } from 'rxjs/operators';
import { promises } from 'fs';
import { readFile } from 'fs';
import {ApartmentLessInfo} from "./ApartmentLessInfo";

@Injectable()
export class ApartmentService implements OnModuleInit{
  private tabApartment: Apartment[] = [];
  private tabFavorites: Apartment[] = [];
  private tabApartmentLessInfo: ApartmentLessInfo[] = [];
  constructor(private httpService:HttpService) {}

  onModuleInit(): void{
    const file = this.httpService
        .get(
            'https://data.opendatasoft.com/explore/dataset/fr_crous_logement_france_entiere@mesr/download/?format=json&timezone=Europe/Berlin&lang=fr',
        )
        .subscribe((response) => this.adaptInApartment(response.data));
  }
  public adaptInApartment(ApartmentI: ApartmentImported[]){
    let tempApartment: Apartment;
    ApartmentI.forEach((apartmentI: ApartmentImported) => {
      tempApartment = {
        id: apartmentI.fields.id,
        nom: apartmentI.fields.title,
        description: apartmentI.fields.mail,
        zone: apartmentI.fields.zone,
        coordX: apartmentI.fields.geocalisation[0],
        coordY: apartmentI.fields.geocalisation[1],
        phone: apartmentI.fields.phone,
        address: apartmentI.fields.address,
        photo: apartmentI.fields.photo
      };
      this.addApartment(tempApartment);
    });
  }

  public addApartment(apartment: Apartment) {
    if (this.getApartment(apartment.nom) === undefined) {
      this.tabApartment.push(apartment);
    }
  }

  public getApartment(name: string): Apartment | undefined {
    let apartment: Apartment;
    let i: number;
    for (i = 0; i < this.tabApartment.length; i++) {
      if (this.tabApartment[i].nom === name) {
        return this.tabApartment[i];
      }
    }
    return undefined;
  }
  public getApartmentID(id: number): Apartment{
      return this.tabApartment.filter(value => value.id === id)[0];
  }

  public getAllApartments(): Apartment[] {
    let listApartment: Apartment[] = [];
    let i: number;
    for (i = 0; i < this.tabApartment.length; i++) {
      listApartment.push(this.tabApartment[i]);
    }
    return listApartment.sort((a, b) => a.nom.localeCompare(b.nom));
  }
  public getFavorites(): Apartment[] {
    let listApartment: Apartment[] = [];
    let i: number;
    for (i = 0; i < this.tabFavorites.length; i++) {
      listApartment.push(this.tabFavorites[i]);
    }
    return listApartment.sort((a, b) => a.nom.localeCompare(b.nom));
  }
  public addMark(id: number): Apartment{
    this.tabFavorites.push(this.getApartmentID(id));
    return this.getApartmentID(id);
  }
  public deleteMark(idToDelete: number): Apartment {
    this.tabFavorites = this.tabFavorites.filter(value => value.id !== idToDelete);
    console.log(this.tabFavorites);
    return this.getApartmentID(idToDelete);
  }
}

