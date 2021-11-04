import { Injectable, OnModuleInit } from '@nestjs/common';
import { Apartment } from './Apartment';
import { ApartmentImported } from './ApartmentImported';

import { buffer } from 'rxjs/operators';
import { promises } from 'fs';
import { readFile } from 'fs';

@Injectable()
export class ApartmentService {
  private tabApartment: Apartment[] = [];
  private tabFavorites: Apartment[] = [];

  async onModuleInit() {
    const file = await promises.readFile('./src/dataset.json');
    let tabFile = JSON.parse(file.toString());
    this.convertFile(tabFile);
  }

  public convertFile(file: any) {
    let apartmentI: ApartmentImported;
    let tempApartment: Apartment;
    console.log(file);
    file.forEach((apartmentI: ApartmentImported) => {
      tempApartment = {
        id: apartmentI.id,
        nom: apartmentI.nom,
        description: apartmentI.description,
        zone: apartmentI.zone,
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
  public addMark(id: number){
     this.tabFavorites.push(this.tabApartment.find(value => value.id === id));
  }
  public deleteMark(id: number) {
    let apartmentD = this.tabApartment.find(value => value.id === id);
    this.tabFavorites = this.tabFavorites.filter(value => value.id !== id);
  }
}
