package com.example.projectcheva

sealed class Screens (val screen:String){
    data object Beranda : Screens("Beranda")
    data object Favorit : Screens("Favorit")
    data object Berita : Screens("Berita")
    data object Artikel : Screens("Artikel")
    data object Profile : Screens("Profil")
}