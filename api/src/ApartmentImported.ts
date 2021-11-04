
export interface Fields {
  photo: string;
  zone: string;
  title: string;
  short_desc: string;
  troubleshootingurl: string;
  appointmenturl: string;
  geocalisation: number[];
  phone: string;
  virtualvisiturl: string;
  contact: string;
  address: string;
  mail: string;
  services: string;
  interneturl: string;
  infos: string;
  id: number;
  bookingurl: string;
}

export interface Geometry {
  type: string;
  coordinates: number[];
}
export interface ApartmentImported {
  datasetid: string;
  recordid: string;
  fields: Fields;
  geometry: Geometry;
  record_timestamp: Date;
}

