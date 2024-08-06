package com.example.projectcheva

object Screens {
    val Splash = Screen("splash")
    val Login = Screen("login")
    val Register = Screen("register")
    val ChangePassword = Screen("change_password")
    val ForgotPassword = Screen("forgot_password")
    val Beranda = Screen("beranda")
    val Favorit = Screen("favorit")
    val Berita = Screen("berita")
    val DetailBerita = Screen("detail_berita")
    val ListBerita = Screen("list_berita")
    val Artikel = Screen("artikel")
    val Budaya = Screen("budaya")
    val DetailBudaya = Screen("detail_budaya")
    val DetailEvent = Screen("detail_event")
    val Profile = Screen("profile")
    val EditProfile = Screen("edit_profile")
    val Loading = Screen("loading")
    val Plan = Screen("plan")
    data class Screen(val route: String)
}
