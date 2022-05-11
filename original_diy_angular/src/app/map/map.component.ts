import {AfterViewInit, Component, OnInit} from '@angular/core';
import * as L from 'leaflet';
import {Title} from "@angular/platform-browser";
import {WorkshopService} from "../service/workshop.service";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit, AfterViewInit {
  private map: any;
  public workshops: any[] | undefined;
  public workshop: any;
  public s3: string = 'https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/';

  constructor(
      private title: Title,
      private workshopService: WorkshopService) {
    this.title.setTitle('Carte')
  }

  ngOnInit(): void {
    this.getAllWorkshopsConfirmed();
  }

  getAllWorkshopsConfirmed(): void {
    this.workshopService.getAllConfirmed().subscribe({
      next: (datas) => {
        this.workshops = datas;

        const blueIcon = L.icon({
          iconUrl: 'assets/img/marker-icon.png',

          iconSize: [40, 60], // size of the icon
          shadowSize: [50, 64], // size of the shadow
          iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
          shadowAnchor: [4, 62],  // the same for the shadow
          popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
        });

        const greenIcon = L.icon({
          iconUrl: 'assets/img/markerIconsGreen.png',

          iconSize: [40, 60], // size of the icon
          shadowSize: [50, 64], // size of the shadow
          iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
          shadowAnchor: [4, 62],  // the same for the shadow
          popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
        });

        const orangeIcon = L.icon({
          iconUrl: 'assets/img/markericonsOrange.png',

          iconSize: [40, 60], // size of the icon
          shadowSize: [50, 64], // size of the shadow
          iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
          shadowAnchor: [4, 62],  // the same for the shadow
          popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
        });

        const redIcon = L.icon({
          iconUrl: 'assets/img/markerIconsRed.png',

          iconSize: [40, 60], // size of the icon
          shadowSize: [50, 64], // size of the shadow
          iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
          shadowAnchor: [4, 62],  // the same for the shadow
          popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
        });

        const iconMarron = L.icon({
          iconUrl: 'assets/img/markericonsMarron.png',

          iconSize: [40, 60], // size of the icon
          shadowSize: [50, 64], // size of the shadow
          iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
          shadowAnchor: [4, 62],  // the same for the shadow
          popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
        });

        this.workshops.forEach((data) => {
          const id = data.id;

          const date = new Date(data.date);
          const month = date.getUTCMonth() >= 10 ? `${date.getUTCMonth()}` : `0${date.getUTCMonth()}`;
          const day = date.getUTCDate() >= 10 ? `${date.getUTCDate()}` : `0${date.getUTCDate()}`;
          const hours = date.getUTCHours() >= 10 ? `${date.getUTCHours()}` : `0${date.getUTCHours()}`;
          const minutes = date.getUTCMinutes() >= 10 ? `${date.getUTCMinutes()}` : `0${date.getUTCMinutes()}`;

    if (data.roles[0].name == "ROLE_ADMIN"){
      switch (data.diyCategory.name) {
        case "Aménagements intérieurs":
          const markerMarron = L.marker([data.latitude, data.longitude], {icon: iconMarron }).addTo(this.map)
            .bindPopup(`
                <div class="justify-content-center">
                    <p class='text-center my-2'>${data.diyCategory.name}</p>
                    <div>
                        <img style="width: 100%" class="picture essai" src="${this.s3 + data.picturePath}" 
                        alt="Image de l'atelier">
                    </div>
                    <div class="text-center fw-bold mt-2"><span>${data.title}</span></div>
                    <div class="mt-3"><i class="bi bi-calendar3"></i><span> : ${day}/${month}/${date.getFullYear()}</span></div>
                    <div class="my-2"><i class="bi bi-stopwatch"></i><span> : ${hours} H ${minutes}</span></div>
                </div>
                <div class="text-center my-4">
                    <a class='btn btn-primary text-white' href='/workshop/${id}'>Détails</a>
                </div>`)
            .openPopup;
          break
        case "Aménagements extérieurs":
          const markerRed = L.marker([data.latitude, data.longitude], {icon: redIcon}).addTo(this.map)
            .bindPopup(`
                <div class="justify-content-center">
                    <p class='text-center my-2'>${data.diyCategory.name}</p>
                    <div>
                        <img style="width: 100%" class="picture essai" src="${this.s3 + data.picturePath}" 
                        alt="Image de l'atelier">
                    </div>
                    <div class="text-center fw-bold mt-2"><span>${data.title}</span></div>
                    <div class="mt-3"><i class="bi bi-calendar3"></i><span> : ${day}/${month}/${date.getFullYear()}</span></div>
                    <div class="my-2"><i class="bi bi-stopwatch"></i><span> : ${hours} H ${minutes}</span></div>
                </div>
                <div class="text-center my-4">
                    <a class='btn btn-primary text-white' href='/workshop/${id}'>Détails</a>
                </div>`)
            .openPopup;
          break
        case "Les Animaux de compagnie":
          const markerOrange = L.marker([data.latitude, data.longitude], {icon: orangeIcon}).addTo(this.map)
            .bindPopup(`
                <div class="justify-content-center">
                    <p class='text-center my-2'>${data.diyCategory.name}</p>
                    <div>
                        <img style="width: 100%" class="picture essai" src="${this.s3 + data.picturePath}" 
                        alt="Image de l'atelier">
                    </div>
                    <div class="text-center fw-bold mt-2"><span>${data.title}</span></div>
                    <div class="mt-3"><i class="bi bi-calendar3"></i><span> : ${day}/${month}/${date.getFullYear()}</span></div>
                    <div class="my-2"><i class="bi bi-stopwatch"></i><span> : ${hours} H ${minutes}</span></div>
                </div>
                <div class="text-center my-4">
                    <a class='btn btn-primary text-white' href='/workshop/${id}'>Détails</a>
                </div>`)
            .openPopup;
          break
        case "Les fêtes de l'année":
          const markerGreen = L.marker([data.latitude, data.longitude], {icon: greenIcon}).addTo(this.map)
            .bindPopup(`
                <div class="justify-content-center">
                    <p class='text-center my-2'>${data.diyCategory.name}</p>
                    <div>
                        <img style="width: 100%" class="picture essai" src="${this.s3 + data.picturePath}" 
                        alt="Image de l'atelier">
                    </div>
                    <div class="text-center fw-bold mt-2"><span>${data.title}</span></div>
                    <div class="mt-3"><i class="bi bi-calendar3"></i><span> : ${day}/${month}/${date.getFullYear()}</span></div>
                    <div class="my-2"><i class="bi bi-stopwatch"></i><span> : ${hours} H ${minutes}</span></div>
                </div>
                <div class="text-center my-4">
                    <a class='btn btn-primary text-white' href='/workshop/${id}'>Détails</a>
                </div>`)
            .openPopup;
          break
      }

    } else {

      const markerBlue = L.marker([data.latitude, data.longitude], {icon: blueIcon}).addTo(this.map)
        .bindPopup(`
                <div class="justify-content-center">
                    <p class='text-center my-2'>${data.diyCategory.name}</p>
                    <div>
                        <img style="width: 100%" class="picture essai" src="${this.s3 + data.picturePath}" 
                        alt="Image de l'atelier">
                    </div>
                    <div class="text-center fw-bold mt-2"><span>${data.title}</span></div>
                    <div class="mt-3"><i class="bi bi-calendar3"></i><span> : ${day}/${month}/${date.getFullYear()}</span></div>
                    <div class="my-2"><i class="bi bi-stopwatch"></i><span> : ${hours} H ${minutes}</span></div>
                </div>
                <div class="text-center my-4">
                    <a class='btn btn-primary text-white' href='/workshop/${id}'>Détails</a>
                </div>`)
        .openPopup;
    }

        });
      },
      error: (err) => console.log(err)
    });
  }

  private initMap(): void {
    const tiles = L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
          maxZoom: 18,
          attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href=“https://www.mapbox.com/”>Mapbox</a>',
          id: 'mapbox/streets-v11',
          tileSize: 512,
          zoomOffset: -1
        }
      ),


      USGS_USImageryTopo = L.tileLayer('https://basemap.nationalmap.gov/arcgis/rest/services/USGSImageryTopo/MapServer/tile/{z}/{y}/{x}', {
          minZoom: 3,
          maxZoom: 20,
          attribution: 'Tiles courtesy of the <a href="https://usgs.gov/">U.S. Geological Survey</a>'
        },
      );

    const cities = L.layerGroup();


    const baseLayers = {
      "Street": tiles,
      "Satelitte": USGS_USImageryTopo
    };



    this.map = L.map('map', {
      center: [47.902964, 1.909251],
      zoom: 10,
      layers: [tiles, cities]
    });

    L.control.layers(baseLayers).addTo(this.map);
  }

  ngAfterViewInit(): void {
    this.initMap();
  }
}
