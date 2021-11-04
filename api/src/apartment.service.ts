import { Injectable, OnModuleInit } from '@nestjs/common';
import { Apartment } from './Apartment';
import { ApartmentImported } from './ApartmentImported';

import { buffer } from 'rxjs/operators';
import { promises } from 'fs';
import { readFile } from 'fs';

@Injectable()
export class ApartmentService {
  private tabApartment: Apartment[] = [];

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
      console.log("test3");
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

  // public getBooksOf(author: string): Book[] {
  //   let tabBookAuthor = this.tabBook.filter(value => value.author === author);
  //   return tabBookAuthor;
  // }
  //
  public getAllApartments(): Apartment[] {
    let listApartment: Apartment[] = [];
    let i: number;
    for (i = 0; i < this.tabApartment.length; i++) {
      listApartment.push(this.tabApartment[i]);
    }
    return listApartment.sort((a, b) => a.nom.localeCompare(b.nom));
  }
  //
  // public getTotalNumberOfBooks(): number {
  //   return this.tabBook.length;
  // }
  //
  // public getBooksPublishedBefore(aDate: string | Date): Book[] {
  //   let date = new Date(aDate);
  //   return this.tabBook.filter(value => value.date.getTime() < date.getTime());
  // }
  //
  // public deleteBook(name: string) {
  //   let bookD = this.tabBook.find(value => value.title === name);
  //   this.tabBook = this.tabBook.filter(value => value.title !== name);
  // }
}
