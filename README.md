# Fermate ATM vicino a me

Un servizio web che ritorna le linee ATM vicino ad un determinato indirizzo.


## Informazioni

L'applicazione è sviluppata in Java, con il supporto del build tool [Maven](https://maven.apache.org/). Anche se molto amati in ambito enterprise, non sono utilizzati framework particolari. Le funzionalità web sono esposte da [questa libreria](https://sparkjava.com/). Il punto di ingresso dell'applicazione è  `stops.calculator.App`. 

Alla partenza, l'applicazione espone l'endpoint `/atm/stops` (sulla porta: `4567`). Dato in input un indirizzo, l'endpoint restituisce le fermate ATM nel raggio di 200 metri (max 10 risultati). L'indirizzo in input viene recuperato dalla  query string della richiesta. L'output è in formato JSON (vedi esempio sotto).

Esempio di utilizzo (con indirizzo `via Larga 26 Milano`):

```bash
❯ curl -s "http://localhost:4567/atm/stops?address=via%20larga%2026%20Milano" | jq .
{
  "stops": [
    {
      "code": "missori",
      "description": "Missori",
      "availableLines": [ "M3" ],
      "position": { "lat": 45.4639037177197, lng": 9.18861101005318 }
    },
    {
      "code": "11793",
      "description": "Via Larga, 4 angolo Via S.Clemente",
      "availableLines": [ "16", "24" ],
      "position": { "lat": 45.462712945354, "lng": 9.19420965647107 }
    }
  ]
}
```


### Dettagli di funzionamento

Per ottenere le coordinate geografiche legate ad un indirizzo si usa il geocoding.  Nel progetto si propone di utilizzare il 

servizio esterno [HERE Geocoding](https://developer.here.com/documentation/geocoding-search-api/dev_guide/topics/endpoint-geocode-brief.html). 

Per semplicità si fornisce una API-KEY già funzionante.

Le informazioni sulle linee ATM sono disponibili in modalità open-data. Sul sito del [comune di Milano](https://dati.comune.milano.it/dataset) 

è possibile ottenere la posizione per le fermate delle [linee di superficie](https://dati.comune.milano.it/dataset/ds534-atm-fermate-linee-di-superficie-urbane/resource/2a52d51d-66fe-480b-a101-983aa2f6cbc3) 

e per [linee della metropolitana](https://dati.comune.milano.it/dataset/ds535_atm-fermate-linee-metropolitane/resource/0f4d4d05-b379-45a4-9a10-412a34708484). 

L'implementazione proposta prevede di calcolare la distanza tra indirizzo e fermate ATM 

usando la [harvesine formula](https://community.esri.com/t5/coordinate-reference-systems-blog/distance-on-a-sphere-the-haversine-formula/ba-p/902128). 

Il calcolo della distanza viene ripetuto per tutte le fermate, ad ogni invocazione del servizio.


## Aggiunto dockerfile

- per creare l'immagine
        eseguire "docker build -t atm-nearme ."
- per eseguire l'immagine
        lanciare "docker run -p 4567:4567 atm-nearme"