<h1 align="center">Pokedex</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Mit"><img alt="License" src="https://img.shields.io/badge/License-Mit%202.0-red.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
Pokedex is a small demo application based on modern Android application tech-stacks with Jetpack Compose and MVVM architecture.<br>This project focuses in particular on the new quick and easy way to build a screen in Android, Jetpack Compose.<br>
Also fetching data from the network with an API via repository pattern.
The application is available in dark and light mode.
</p>
</br>

## Screenshoots

<img src="/previews/screen_dark_1.jpg" width="23%"/>&emsp;
<img src="/previews/screen_dark_2.jpg" width="23%"/>&emsp;
<img src="/previews/screen_dark_3.jpg" width="23%"/>&emsp;
<img src="/previews/screen_dark_4.jpg" width="23%"/>&emsp;</br>


<img src="/previews/screen_light_1.jpg" width="23%"/>&emsp;
<img src="/previews/screen_light_2.jpg" width="23%"/>&emsp;
<img src="/previews/screen_light_3.jpg" width="23%"/>&emsp;
<img src="/previews/screen_light_4.jpg" width="23%"/>&emsp;

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) - For dependency injection.
- Jetpack
  - [Compose](https://developer.android.com/jetpack/compose) - Building UI simplifie and accelerate.
  - Lifecycle - Dispose of observing data when lifecycle state changes.
  - [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Navigation support for Jetpack Compose.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Architecture (Model - View - ViewModel)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A JSON library for Kotlin.
- [Accompanist Coil](https://github.com/google/accompanist/tree/main/coil) - Loading images.

## Design
Thanks for the inspiration to :</br>
[Pokedex App](https://dribbble.com/shots/6540871-Pokedex-App) by **[Saepul Nahwan](https://dribbble.com/saepulnahwan23)**</br>
[Pokédex](https://dribbble.com/shots/14241781-Pok-dex) by **[Bruna Campos](https://dribbble.com/brucampos)**

## MAD Score
<img src="/previews/summary.png" />
<img src="/previews/kotlin.png" />

## Architecture
Pokedex is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)

## PokéAPI

<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="right" width="21%"/>

Pokedex using the [PokeAPI](https://pokeapi.co/) for constructing RESTful API.<br>
PokeAPI provides a RESTful API with lots of Pokémon related data.

# License
```xml
MIT License

Copyright (c) 2021 Florian Trecul

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

