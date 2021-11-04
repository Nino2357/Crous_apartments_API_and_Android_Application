import {HttpService, Injectable, OnModuleInit} from '@nestjs/common';
import { Apartment } from './Apartment';
import { ApartmentImported } from './ApartmentImported';

import { buffer } from 'rxjs/operators';
import { promises } from 'fs';
import { readFile } from 'fs';

@Injectable()
export class ApartmentService implements OnModuleInit{
  private tabApartment: Apartment[] = [];
  private tabFavorites: Apartment[] = [];
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
      };
      this.addApartment(tempApartment);
    });
  }

  // public convertFile(file: any) {
  //   let apartmentI: ApartmentImported;
  //   let tempApartment: Apartment;
  //   console.log(file);
  //   file.forEach((apartmentI: ApartmentImported) => {
  //     tempApartment = {
  //       id: apartmentI.id,
  //       nom: apartmentI.nom,
  //       description: apartmentI.description,
  //       zone: apartmentI.zone,
  //     };
  //
  //     this.addApartment(tempApartment);
  //   });
  // }

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
  public getApartmentID(id: number): Apartment {
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
    let newFavorite: Apartment = this.tabApartment.find(value => value.id === id);
    this.tabFavorites.push(newFavorite);
    return newFavorite;
  }
  public deleteMark(id: number): Apartment {
    let apartmentD = this.tabApartment.find(value => value.id === id);
    this.tabFavorites = this.tabFavorites.filter(value => value.id !== id);
    return apartmentD;
  }
}
