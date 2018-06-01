# SAutoMSG
###### Plugin na automatyczne wiadomości
## Specyfikacja techniczna
1. Wersja serwera: ``` 1.8x ```
2. Silnik: ``` Spigot/PaperSpigot/Craftbukkit ```
3. Wymagane pluginy: ``` Brak ```
4. Java: ``` 1.8 ```
## Instalacja
1. Pobierz plik [JAR](https://github.com/SopelPL/SAutoMSG/releases)
2. Wgraj plik na serwer ``` /plugins ```
3. Uruchom lub ~~zrestartuj~~ serwer
4. Zatrzymaj serwer i skonfiguruj plugin ``` /plugins/SAutoMSG/config.yml ```
5. Ponownie uruchom serwer
6. Gotowe!
## Konfiguracja
##### Aby skonfigurować plugin ****poprawnie**** trzeba rozumieć co oznaczają dane pola.
1. ```tag: '&7[&a&lINFO&7]'``` - **Tutaj podajemy prefix**
2. ``` interval: 10 ``` - **Tutaj wpisujemy co ile sekund ma wyświetlać wiadomość**
3. ```admin-permission: 'info.admin'``` - **Tutaj wpisujemy permisje (nie potrzebna)**
4. ```status: 'true'``` - **NIE RUSZAĆ! Linijka ta odpowiada za włączanie/wyłączanie wysyłania wiadomości. (Plugin sam się zajmie tą linijką)**
5. ```messages:``` - **Tutaj podajemy wiadomości zamykając je w ``` '' ```. Znak koloru ``` & ```**
6. UWAGA! KAŻDA WIADOMOŚĆ TO NOWA LINIA (2 SPACJE ODSTĘPU OD BRZEGU)
> Przykładowy config (``` [] ``` oznacza 1 spacje):
```
tag: '&7[&a&lINFO&7]'
interval: 10
admin-permission: 'info.admin'
status: true
messages:
[][]- 'Witaj na serwerze!\nNikt nie cierpi!'
[][]- 'Baw sie dobrze!'
[][]- '&cCheaterow zglaszaj na &a/helpop'
[][]- 'Admin nie poprosi o hsalo!'
[][]- 'Na serwerze nie ma graczy'
[][]- 'Tab jest autorski!'
```
## Pytania i odpowiedzi
1. Skąd plugin wie którą wiadomość ma wysłać? ``` Odpowiedź jest prosta. Plugin losuje wiadomość i wysyła ją na serwer. ```
2. Czy plugin nie będzie obciążał serwera? ``` Nie pod warunkiem, że nie ustawisz czasu mniejszego niż 5 sekund. ```
3. Jak plugin liczy czas? Czyżby pętle? ``` Nic z tych żeczy. Plugin do odliczania używa Timer i TimerTask ```
4. Pod jaką wersje jest plugin? ``` Plugin pisany był pod silnik Spigot 1.8 ```
5. Jakich pluginów potrzebuje do działania? ``` Żadnych! Plugin nie używa innych pluginów do działania. Właściwie to jeśli będzie on jedynym pluginem na serwerze to też będzie działał. ```
6. Czy aby pokolorować tekst muszę używać **§**? ``` Nie. Ty używasz standardowego znaku (&). Plugin sobie to potem zamienia ```
7. **Jeśli brakuje tu jakiegoś pytania napisz do mnie ``` sopelplyt@gmail.com ```**
