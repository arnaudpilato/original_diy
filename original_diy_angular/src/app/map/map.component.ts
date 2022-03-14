import {AfterViewInit, Component, OnInit} from '@angular/core';
import * as L from 'leaflet';
import {DiyWorkshop} from "../model/workshop.model";
import {Title} from "@angular/platform-browser";
import {WorkshopService} from "../service/workshop.service";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit, AfterViewInit {
  private map: any;

  public workshops: DiyWorkshop[] | undefined;


  constructor(private title: Title, private workshopService: WorkshopService) {
    this.title.setTitle("OriginalDIY - Admin - Workshops")
  }

  ngOnInit(): void {
    this.getAllWorkshopsConfirmed();
  }

  getAllWorkshopsConfirmed(): void {
    this.workshopService.getAllConfirmed().subscribe({
      next: (datas) => {
        this.workshops = datas;

        const greenIcon = L.icon({
          iconUrl: 'assets/img/markericons.png',

          iconSize: [40, 60], // size of the icon
          shadowSize: [50, 64], // size of the shadow
          iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
          shadowAnchor: [4, 62],  // the same for the shadow
          popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
        });

        this.workshops.forEach((data) => {

          const date = new Date(data.date);
          const month = date.getUTCMonth() >= 10 ? `${date.getUTCMonth()}` : `0${date.getUTCMonth()}`;
          const day = date.getUTCDate() >= 10 ? `${date.getUTCDate()}` : `0${date.getUTCDate()}`;
          const hours = date.getUTCHours() >= 10 ? `${date.getUTCHours()}` : `0${date.getUTCHours()}`;
          const minutes = date.getUTCMinutes() >= 10 ? `${date.getUTCMinutes()}` : `0${date.getUTCMinutes()}`;

          const marker = L.marker([data.latitude, data.longitude], {icon: greenIcon}).addTo(this.map)
            .bindPopup(`<p class='my-2'>${data.title}</p>`
              + `<p> Prévue le :  ${day}/${month}/${date.getFullYear()}</p>` +
              `<p> à : ${hours} H ${minutes}</p>`
              + "<br/>" +
              "<button class='btn btn-primary'>Détail</button>")
            .openPopup;
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

    const overlays = {
      "Utilisateurs": cities
    };

    this.map = L.map('map', {
      center: [47.902964, 1.909251],
      zoom: 10,
      layers: [tiles, cities]
    });

    L.control.layers(baseLayers, overlays).addTo(this.map);
  }


  ngAfterViewInit(): void {
    this.initMap();

  }
}
