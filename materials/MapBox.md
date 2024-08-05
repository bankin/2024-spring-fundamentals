# Integrate MapBox

## Add library in html
- In head tag add
```HTML
<link href="https://api.mapbox.com/mapbox-gl-js/v3.5.1/mapbox-gl.css" rel="stylesheet">
<script src="https://api.mapbox.com/mapbox-gl-js/v3.5.1/mapbox-gl.js"></script>
```

 - In body tag before closing

```HTML
<script>
mapboxgl.accessToken = 'accessToken';
const map = new mapboxgl.Map({
    container: 'map',
    // Choose from Mapbox's core styles, or make your own style with Mapbox Studio
    style: 'mapbox://styles/mapbox/streets-v12',
    center:  [-122.4861, 37.828802],
    zoom: 11
});

map.on('load', () => {
    map.addSource('route', {
        'type': 'geojson',
        'data': {
            'type': 'Feature',
            'properties': {},
            'geometry': {
                'type': 'LineString',
                'coordinates': [
                    [-122.483696, 37.833818],
                    [-122.483482, 37.833174],
                    [-122.483396, 37.8327],
                    [-122.483568, 37.832056],
                    [-122.48404, 37.831141],
                    [-122.48404, 37.830497],
                    [-122.483482, 37.82992],
                    [-122.483568, 37.829548],
                    [-122.48507, 37.829446],
                    [-122.4861, 37.828802],
                    [-122.486958, 37.82931],
                    [-122.487001, 37.830802],
                    [-122.487516, 37.831683],
                    [-122.488031, 37.832158],
                    [-122.488889, 37.832971],
                    [-122.489876, 37.832632],
                    [-122.490434, 37.832937],
                    [-122.49125, 37.832429],
                    [-122.491636, 37.832564],
                    [-122.492237, 37.833378],
                    [-122.493782, 37.833683]
                ]
            }
        }
    });
    map.addLayer({
        'id': 'route',
        'type': 'line',
        'source': 'route',
        'layout': {
            'line-join': 'round',
            'line-cap': 'round'
        },
        'paint': {
            'line-color': '#888',
            'line-width': 8
        }
    });
});
</script>
```

## Fetch coordinates for current route
``` JS
const routeId = document.getElementById('routeId').value;

fetch(`http://localhost:8080/api/routes/coordinates/${routeId}`)
```

## Find middle coordinates

``` JS
const xCoordinates = coordinates.map(xAndY => xAndY[0]);
const minX = Math.min(...xCoordinates);
const maxX = Math.max(...xCoordinates);

const yCoordinates = coordinates.map(xAndY => xAndY[1]);
const minY = Math.min(...yCoordinates);
const maxY = Math.max(...yCoordinates);

const middleX = (minX + maxX) / 2;
const middleY = (minY + maxY) / 2;
```

## Replace 
``` JS
// With middle coordinates
center:  [-122.4861, 37.828802]

// With recieve coordinates form call
coordinates:  [
    [-122.483696, 37.833818],
    [-122.483482, 37.833174],
    ...
]
```


# Result Script
```HTML
<script>
    let coordinates;
    const routeId = document.getElementById('routeId').value
    let midCoordinates;

    fetch(`http://localhost:8080/api/routes/coordinates/${routeId}`)
        .then(res => res.json())
        .then(res => {
            coordinates = res;

            const xCoordinates = coordinates.map(xAndY => xAndY[0]);
            const minX = Math.min(...xCoordinates);
            const maxX = Math.max(...xCoordinates);

            const yCoordinates = coordinates.map(xAndY => xAndY[1]);
            const minY = Math.min(...yCoordinates);
            const maxY = Math.max(...yCoordinates);

            const middleX = (minX + maxX) / 2;
            const middleY = (minY + maxY) / 2;

            midCoordinates = [middleX, middleY];

            mapboxgl.accessToken = 'accessToken';
            const map = new mapboxgl.Map({
                container: 'map',
                // Choose from Mapbox's core styles, or make your own style with Mapbox Studio
                style: 'mapbox://styles/mapbox/streets-v12',
                center: midCoordinates,
                zoom: 11
            });

            map.on('load', () => {
                map.addSource('route', {
                    'type': 'geojson',
                    'data': {
                        'type': 'Feature',
                        'properties': {},
                        'geometry': {
                            'type': 'LineString',
                            'coordinates': coordinates
                        }
                    }
                });
                map.addLayer({
                    'id': 'route',
                    'type': 'line',
                    'source': 'route',
                    'layout': {
                        'line-join': 'round',
                        'line-cap': 'round'
                    },
                    'paint': {
                        'line-color': '#888',
                        'line-width': 8
                    }
                });
            });
        })
</script>

```