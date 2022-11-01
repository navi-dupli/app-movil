package co.navidupli.vinilos

sealed class Screen(val route: String) {
    object RootScren : Screen("root_screen")
    object AppScaffold : Screen("app_scaffold")
}