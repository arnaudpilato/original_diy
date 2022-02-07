import { AfterViewInit, Component, OnInit } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit, AfterViewInit {
  private map: any;

  constructor() { }

  ngOnInit(): void {
  }

  private initMap(): void {
    const tiles = L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
      maxZoom: 18,
      attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href=“https://www.mapbox.com/”>Mapbox</a>',
      id: 'mapbox/streets-v11',
      tileSize: 512,
      zoomOffset: -1
    }),

      USGS_USImageryTopo = L.tileLayer('https://basemap.nationalmap.gov/arcgis/rest/services/USGSImageryTopo/MapServer/tile/{z}/{y}/{x}', {
      minZoom: 3,
      maxZoom: 20,
      attribution: 'Tiles courtesy of the <a href="https://usgs.gov/">U.S. Geological Survey</a>'
    });

    const cities = L.layerGroup();

    const baseLayers = {
      "Street": tiles,
      "Satelitte" : USGS_USImageryTopo
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
